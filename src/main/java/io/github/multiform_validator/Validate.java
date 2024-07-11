package io.github.multiform_validator;

import java.util.*;
import java.util.regex.*;

import static io.github.multiform_validator.Validator.isEmail;

public class Validate {
    private Validate() {
        throw new IllegalStateException("Utility class");
    }

    private static final List<String> validateEmailValidDomainsDefault = Arrays.asList(
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
            "@protonmail.ch"
    );

    public static class ValidateEmailOptionsParams {
        public int maxLength = 400;
        public String country = "";
        public boolean validDomains = false;
        public List<String> validDomainsList = new ArrayList<>();
    }

    private static final ValidateEmailOptionsParams validateEmailDefaultOptionsParams = new ValidateEmailOptionsParams();

    // Overloaded methods
    public static boolean validateEmail(String email) {
        return validateEmail(email, null);
    }
    public static boolean validateEmail(String email, ValidateEmailOptionsParams options) {
        if (options == null) {
            options = validateEmailDefaultOptionsParams;
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Input value cannot be empty.");
        }

        if (options.validDomains && !options.validDomainsList.isEmpty()) {
            throw new IllegalArgumentException("Cannot use validDomains and validDomainsList at the same time.");
        }

        int maxLength = options.maxLength > 0 ? options.maxLength : validateEmailDefaultOptionsParams.maxLength;
        String country = options.country != null ? options.country : validateEmailDefaultOptionsParams.country;
        List<String> validDomains = options.validDomains ? validateEmailDefaultOptionsParams.validDomainsList : options.validDomainsList;

        // Initialize with an empty regular expression
        Pattern regex = Pattern.compile("");
        List<String> validDomainsCustom = new ArrayList<>();

        if (validDomains != null && !validDomains.isEmpty()) {
            for (String domain : validDomains) {
                validDomainsCustom.add(domain.replaceAll("[.*+?^${}()|\\[\\]\\\\]", "\\\\$0"));
            }
            regex = Pattern.compile(String.join("|", validDomainsCustom) + "$", Pattern.CASE_INSENSITIVE);
        } else if (validDomains != null) {
            regex = Pattern.compile(String.join("|", validateEmailValidDomainsDefault) + "$", Pattern.CASE_INSENSITIVE);
        }

        // Use the Matcher to check if the email ends with one of the valid domains
        Matcher matcher = regex.matcher(email);
        if (!matcher.find()) {
            return false;
        }

        if (!isEmail(email)) {
            return false;
        }

        if (email.length() > maxLength) {
            return false;
        }

        // If country is provided, check if the email ends with the country code
        if (country != null && !country.isEmpty()) {
            return email.endsWith("." + country);
        }

        return true;
    }
}