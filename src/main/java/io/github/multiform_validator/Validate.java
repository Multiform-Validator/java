package io.github.multiform_validator;

import java.util.*;
import java.util.regex.*;

import static io.github.multiform_validator.Validator.isEmail;

public class Validate {
    // Prevent instantiation
    private Validate() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * The default list of valid email domains.
     */
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

    /**
     * The ValidateEmailOptionsParams class represents the options for email validation.
     */
    public static class ValidateEmailOptionsParams {
        public int maxLength = 400;
        public String country = "";
        public boolean validDomains = false;
        public List<String> validDomainsList = new ArrayList<>();
    }

    // Default options for email validation
    private static final ValidateEmailOptionsParams validateEmailDefaultOptionsParams = new ValidateEmailOptionsParams();

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
     * @throws IllegalArgumentException if the input value is empty or if both validDomains and validDomainsList are used at the same time.
     */
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