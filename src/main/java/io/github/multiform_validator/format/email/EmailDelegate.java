package io.github.multiform_validator.format.email;

import io.github.multiform_validator.format.EmailValidator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class EmailDelegate {
  private EmailDelegate() {
    throw new IllegalStateException("Utility class");
  }

  /** The default list of valid email domains. */
  protected static final List<String> VALID_DOMAINS =
      Arrays.asList(
          "@gmail.com",
          "@outlook.com",
          "@yahoo.com",
          "@icloud.com",
          "@hotmail.com",
          "@mail.ru",
          "@yandex.ru",
          "@gmx.com",
          "@zoho.com",
          "@protonmail.com",
          "@protonmail.ch");

  protected static final List<String> GetOnlyEmailCleanAfterDefaultDomain =
      Arrays.asList(".br", ".io", ".pt", ".us", ".org", ".com");

  public static @NotNull ValidateEmailBuilder validateOptions(ValidateEmailBuilder options) {
    if (options == null) {
      options = new ValidateEmailBuilder();
    }

    if (options.isValidDomains() && !options.getValidDomainsList().isEmpty()) {
      throw new IllegalArgumentException(
          "Cannot use validDomains and validDomainsList at the same time.");
    }

    return options;
  }

  public static @NotNull Pattern createRegexPattern(@NotNull ValidateEmailBuilder options) {
    List<String> validDomains =
        options.isValidDomains()
            ? new ValidateEmailBuilder().getValidDomainsList()
            : options.getValidDomainsList();
    List<String> validDomainsCustom;
    if (validDomains != null && !validDomains.isEmpty()) {
      validDomainsCustom =
          validDomains.stream()
              .map(domain -> domain.replaceAll("[.*+?^${}()|\\[\\]\\\\]", "\\\\$0"))
              .collect(Collectors.toList());
      return getPattern(validDomainsCustom);
    }

    if (validDomains != null) {
      return getPattern(VALID_DOMAINS);
    }

    return Pattern.compile("");
  }

  public static @NotNull Pattern getPattern(List<String> validDomainsCustom) {
    return Pattern.compile(String.join("|", validDomainsCustom) + "$", Pattern.CASE_INSENSITIVE);
  }

  public static boolean validateEmailWithRegex(
      String email, @NotNull Pattern regex, ValidateEmailBuilder options) {
    if (!regex.matcher(email).find()
        || !EmailValidator.isEmail(email)
        || email.length() > options.getMaxLength()) {
      return false;
    }

    if (options.getCountry() == null || options.getCountry().isEmpty()) {
      return true;
    }

    return email.endsWith("." + options.getCountry());
  }

  public static @NotNull OnlyEmailParams validateOptions(OnlyEmailParams options) {
    if (options == null) {
      options = new OnlyEmailParams();
    }

    return options;
  }

  public static @NotNull List<String> getEmailMatches(String text) {
    Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
    Matcher matcher = emailPattern.matcher(text);

    List<String> matches = new ArrayList<>();
    while (matcher.find()) {
      matches.add(matcher.group());
    }

    return matches;
  }

  public static @NotNull List<String> cleanDomain(
      @NotNull List<String> emails, Object cleanDomain) {
    List<String> domainsToClean;

    if (cleanDomain instanceof List) {
      @SuppressWarnings("unchecked")
      List<String> castedList = (List<String>) cleanDomain;
      domainsToClean = castedList;
    } else {
      domainsToClean = GetOnlyEmailCleanAfterDefaultDomain;
    }

    List<String> cleanedEmails = new ArrayList<>();
    for (String email : emails) {
      for (String domain : domainsToClean) {
        int index = email.lastIndexOf(domain);
        if (index != -1) {
          email = email.substring(0, index + domain.length());
          break;
        }
      }

      for (String domain : domainsToClean) {
        int index = email.indexOf(domain);
        if (index != -1) {
          email = email.substring(0, index + domain.length());
          break;
        }
      }
      cleanedEmails.add(email);
    }

    return cleanedEmails;
  }

  public static Object handleRepeatEmail(List<String> emails, @NotNull OnlyEmailParams options) {
    if (Boolean.FALSE.equals(options.getRepeatEmail())) {
      Set<String> uniqueEmails = new LinkedHashSet<>(emails);
      return Boolean.TRUE.equals(options.getMultiple())
          ? new ArrayList<>(uniqueEmails)
          : uniqueEmails.iterator().next();
    }

    return Boolean.TRUE.equals(options.getMultiple()) ? emails : emails.get(0);
  }
}
