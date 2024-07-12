package io.github.multiform_validator;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.*;

import static io.github.multiform_validator.Validator.isEmail;

public class Validate {
    // Prevent instantiation
    private Validate() {
        throw new IllegalStateException("Utility class");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // validateEmail
    
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
        options = validateOptions(options);

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Input value cannot be empty.");
        }

        Pattern regex = createRegexPattern(options);

        return validateEmailWithRegex(email, regex, options);
    }

    private static @NotNull ValidateEmailOptionsParams validateOptions(ValidateEmailOptionsParams options) {
        if (options == null) {
            options = validateEmailDefaultOptionsParams;
        }

        if (options.validDomains && !options.validDomainsList.isEmpty()) {
            throw new IllegalArgumentException("Cannot use validDomains and validDomainsList at the same time.");
        }

        return options;
    }

    private static @NotNull Pattern createRegexPattern(@NotNull ValidateEmailOptionsParams options) {
        List<String> validDomains = options.validDomains ? validateEmailDefaultOptionsParams.validDomainsList : options.validDomainsList;

        List<String> validDomainsCustom = new ArrayList<>();
        if (validDomains != null && !validDomains.isEmpty()) {
            for (String domain : validDomains) {
                validDomainsCustom.add(domain.replaceAll("[.*+?^${}()|\\[\\]\\\\]", "\\\\$0"));
            }
            return Pattern.compile(String.join("|", validDomainsCustom) + "$", Pattern.CASE_INSENSITIVE);
        } else if (validDomains != null) {
            return Pattern.compile(String.join("|", validateEmailValidDomainsDefault) + "$", Pattern.CASE_INSENSITIVE);
        }

        return Pattern.compile("");
    }

    private static boolean validateEmailWithRegex(String email, @NotNull Pattern regex, ValidateEmailOptionsParams options) {
        Matcher matcher = regex.matcher(email);
        if (!matcher.find()) {
            return false;
        }

        if (!isEmail(email)) {
            return false;
        }

        if (email.length() > options.maxLength) {
            return false;
        }

        if (options.country != null && !options.country.isEmpty()) {
            return email.endsWith("." + options.country);
        }

        return true;
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
}