package io.github.multiform_validator;

import java.util.*;
import java.util.regex.*;

public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    private static final List<String> GetOnlyEmailCleanAfterDefaultDomain = Arrays.asList(
            ".br", ".io", ".pt", ".us", ".org", ".com"
    );

    public static class GetOnlyEmailOptionsParams {
        public Boolean multiple = false;
        public Object cleanDomain = false;
        public Boolean repeatEmail = false;
    }

    private static final GetOnlyEmailOptionsParams getOnlyEmailDefaultOptionsParams = new GetOnlyEmailOptionsParams();

    public static Object getOnlyEmail(String text, GetOnlyEmailOptionsParams options) {
        if (options == null) {
            options = getOnlyEmailDefaultOptionsParams;
        }

        Boolean multiple = options.multiple != null ? options.multiple : getOnlyEmailDefaultOptionsParams.multiple;
        Object cleanDomain = options.cleanDomain != null ? options.cleanDomain : getOnlyEmailDefaultOptionsParams.cleanDomain;
        Boolean repeatEmail = options.repeatEmail != null ? options.repeatEmail : getOnlyEmailDefaultOptionsParams.repeatEmail;

        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
        Matcher matcher = emailPattern.matcher(text);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }

        if (matches.isEmpty()) {
            return "No email found";
        }

        if (cleanDomain != null && !cleanDomain.equals(false)) {
            List<String> domainsToClean = cleanDomain instanceof List ? (List<String>) cleanDomain : GetOnlyEmailCleanAfterDefaultDomain;

            List<String> cleanedEmails = new ArrayList<>();
            for (String email : matches) {
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

            if (Boolean.FALSE.equals(repeatEmail)) {
                Set<String> uniqueEmails = new LinkedHashSet<>(cleanedEmails);
                return Boolean.TRUE.equals(multiple) ? new ArrayList<>(uniqueEmails) : uniqueEmails.iterator().next();
            }

            return Boolean.TRUE.equals(multiple) ? cleanedEmails : cleanedEmails.get(0);
        }

        if (Boolean.FALSE.equals(repeatEmail)) {
            Set<String> uniqueEmails = new LinkedHashSet<>(matches);
            return Boolean.TRUE.equals(multiple) ? new ArrayList<>(uniqueEmails) : uniqueEmails.iterator().next();
        }

        return Boolean.TRUE.equals(multiple) ? matches : matches.get(0);
    }
}
