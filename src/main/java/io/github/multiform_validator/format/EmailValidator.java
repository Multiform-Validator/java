package io.github.multiform_validator.format;

import io.github.multiform_validator.format.email.EmailDelegate;
import io.github.multiform_validator.format.email.OnlyEmailParams;
import io.github.multiform_validator.format.email.ValidateEmailBuilder;
import java.util.*;
import java.util.regex.Pattern;

public class EmailValidator {
  private EmailValidator() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Checks if the given string is a valid email address.
   *
   * @param email the string to be checked
   * @return true if the string is a valid email address, false otherwise
   * @throws NullPointerException if the email is null
   */
  public static boolean isEmail(String email) {
    if (email == null) {
      throw new NullPointerException("Email cannot be null");
    }

    final Pattern startsWithSpecialChar = Pattern.compile("^[^a-zA-Z]");

    if (startsWithSpecialChar.matcher(email).find()) {
      return false;
    }

    final Pattern regex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    if (!regex.matcher(email).find()) {
      return false;
    }

    final int beforeAt = email.indexOf("@");
    final int afterAt = email.indexOf("@") + 1;
    final int afterLastDot = email.lastIndexOf(".");

    if (Character.isDigit(email.charAt(afterAt))) {
      return false;
    }

    if (Character.isDigit(email.charAt(afterLastDot))) {
      return false;
    }

    if (email.substring(0, beforeAt).contains("..")) {
      return false;
    }

    if (email.substring(0, beforeAt).endsWith(".")) {
      return false;
    }

    final String[] parts = email.split("\\.");

    if (parts.length > 2 && parts[parts.length - 2].equals(parts[parts.length - 3])) {
      return false;
    }

    // Check if there is more than one @
    if (email.split("@").length - 1 > 1) {
      return false;
    }

    if (email.substring(afterAt).contains("..")) {
      return false;
    }

    String[] domainParts = email.split("@")[1].split("\\.");
    Set<String> uniqueDomainParts = new HashSet<>(Arrays.asList(domainParts));

    return domainParts.length == uniqueDomainParts.size();
  }

  /**
   * Validates an email address using the default options.
   *
   * @param email The email address to validate.
   * @return true if the email address is valid, false otherwise.
   * @throws IllegalArgumentException if the input value is empty.
   */
  public static boolean validateEmail(String email) {
    return validateEmail(email, null);
  }

  /**
   * Validates an email address using the specified options.
   *
   * @param email The email address to validate.
   * @param options The options for email validation.
   * @return true if the email address is valid, false otherwise.
   * @throws IllegalArgumentException if the input value is empty or if both validDomains and
   *     validDomainsList are used at the same time.
   */
  public static boolean validateEmail(String email, ValidateEmailBuilder options) {
    options = EmailDelegate.validateOptions(options);

    if (email == null || email.isEmpty()) {
      throw new IllegalArgumentException("Input value cannot be empty.");
    }

    Pattern regex = EmailDelegate.createRegexPattern(options);
    return EmailDelegate.validateEmailWithRegex(email, regex, options);
  }

  /**
   * Extracts email addresses from the given text.
   *
   * @param text The text to extract email addresses from.
   * @param options The options for extracting email addresses.
   * @return The extracted email addresses.
   */
  public static Object getOnlyEmail(String text, OnlyEmailParams options) {
    options = EmailDelegate.validateOptions(options);

    List<String> matches = EmailDelegate.getEmailMatches(text);

    if (matches.isEmpty()) {
      return "No email found";
    }

    if (!options.getCleanDomain().equals(false)) {
      matches = EmailDelegate.cleanDomain(matches, options.getCleanDomain());
    }

    return EmailDelegate.handleRepeatEmail(matches, options);
  }
}
