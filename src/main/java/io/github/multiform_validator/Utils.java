package io.github.multiform_validator;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.*;

public class Utils {
    // Prevent instantiation
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // getOnlyEmail
    private static final List<String> GetOnlyEmailCleanAfterDefaultDomain = Arrays.asList(
            ".br", ".io", ".pt", ".us", ".org", ".com"
    );

    /**
     * Options for the getOnlyEmail method.
     */
    public static class GetOnlyEmailOptionsParams {
        public Boolean multiple = false;
        public Object cleanDomain = false;
        public Boolean repeatEmail = false;
    }

    private static final GetOnlyEmailOptionsParams getOnlyEmailDefaultOptionsParams = new GetOnlyEmailOptionsParams();

    /**
     * Extracts email addresses from the given text.
     *
     * @param text    The text to extract email addresses from.
     * @param options The options for extracting email addresses.
     * @return The extracted email addresses.
     */
    public static Object getOnlyEmail(String text, GetOnlyEmailOptionsParams options) {
        options = validateOptions(options);

        List<String> matches = getEmailMatches(text);

        if (matches.isEmpty()) {
            return "No email found";
        }

        if (options.cleanDomain != null && !options.cleanDomain.equals(false)) {
            matches = cleanDomain(matches, options.cleanDomain);
        }

        return handleRepeatEmail(matches, options);
    }

    private static @NotNull GetOnlyEmailOptionsParams validateOptions(GetOnlyEmailOptionsParams options) {
        if (options == null) {
            options = getOnlyEmailDefaultOptionsParams;
        }

        options.multiple = options.multiple != null ? options.multiple : getOnlyEmailDefaultOptionsParams.multiple;
        options.cleanDomain = options.cleanDomain != null ? options.cleanDomain : getOnlyEmailDefaultOptionsParams.cleanDomain;
        options.repeatEmail = options.repeatEmail != null ? options.repeatEmail : getOnlyEmailDefaultOptionsParams.repeatEmail;

        return options;
    }

    private static @NotNull List<String> getEmailMatches(String text) {
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
        Matcher matcher = emailPattern.matcher(text);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches;
    }

    private static @NotNull List<String> cleanDomain(@NotNull List<String> emails, Object cleanDomain) {
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

    private static Object handleRepeatEmail(List<String> emails, @NotNull GetOnlyEmailOptionsParams options) {
        if (Boolean.FALSE.equals(options.repeatEmail)) {
            Set<String> uniqueEmails = new LinkedHashSet<>(emails);
            return Boolean.TRUE.equals(options.multiple) ? new ArrayList<>(uniqueEmails) : uniqueEmails.iterator().next();
        }

        return Boolean.TRUE.equals(options.multiple) ? emails : emails.get(0);
    }
}