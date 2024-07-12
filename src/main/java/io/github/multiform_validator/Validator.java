package io.github.multiform_validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Validator {
    private static final String INPUT_VALUE_CANNOT_BE_EMPTY = "Input value cannot be empty.";

    private Validator() {
        throw new IllegalStateException("Utility class");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isAscii

    /**
     * Checks if the given string contains only ASCII characters.
     *
     * @param value the string to be checked
     * @return true if the string contains only ASCII characters, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isAscii(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        return value.chars().allMatch(c -> c < 128);
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isBase64

    /**
     * Checks if the given string is a valid Base64 encoded string.
     *
     * @param value the string to be checked
     * @return true if the string is a valid Base64 encoded string, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isBase64(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        return value.matches("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isCEP

    /**
     * Checks if the given string is a valid CEP (Brazilian postal code).
     *
     * @param cep the string to be checked
     * @return true if the string is a valid CEP, false otherwise
     */
    public static boolean isCEP(String cep) {
        if (cep.length() < 8 || cep.length() > 10) {
            return false;
        }

        final String cepString = cep.replaceAll("\\D", "");

        if (cepString.length() != 8) {
            return false;
        }

        try {
            Integer.parseInt(cepString);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isDate

    /**
     * Checks if the given string is a valid date in the format "yyyy-MM-dd".
     *
     * @param date the string to be checked
     * @return true if the string is a valid date, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isDecimal

    /**
     * Checks if the given string is a valid decimal number.
     *
     * @param value the string to be checked
     * @return true if the string is a valid decimal number, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isDecimal(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        try {
            double parsedValue = Double.parseDouble(value);

            return parsedValue % 1 != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isEmail

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

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isMACAddress

    /**
     * Checks if the given string is a valid MAC address.
     *
     * @param macAddress the string to be checked
     * @return true if the string is a valid MAC address, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isMACAddress(String macAddress) {
        if (macAddress == null || macAddress.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        return macAddress.matches("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isMD5

    /**
     * Checks if the given string is a valid MD5 hash.
     *
     * @param value the string to be checked
     * @return true if the string is a valid MD5 hash, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isMD5(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        return value.matches("^[a-fA-F0-9]{32}$");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isNumber

    /**
     * Checks if the given string is a valid number.
     *
     * @param value the string to be checked
     * @return true if the string is a valid number, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isNumber(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        return value.matches("^-?\\d+$");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isPort

    /**
     * Checks if the given port number is valid.
     *
     * @param port the port number to be checked
     * @return true if the port number is valid, false otherwise
     */
    public static boolean isPort(int port) {
        return port >= 0 && port <= 65535;
    }

    /**
     * Checks if the given string is a valid port number.
     *
     * @param port the string to be checked
     * @return true if the string is a valid port number, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isPort(String port) {
        if (port == null || port.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        try {
            final int portNumber = Integer.parseInt(port);
            return portNumber >= 0 && portNumber <= 65535;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isPostalCode

    /**
     * Checks if the given string is a valid postal code.
     *
     * @param postalCode the string to be checked
     * @return true if the string is a valid postal code, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isPostalCode(String postalCode) {
        if (postalCode == null || postalCode.isEmpty()) {
            throw new IllegalArgumentException("Input value must be a string.");
        }

        final String usZipCodeRegex = "^\\d{5}(-\\d{4})?$";
        final String canadaPostalCodeRegex = "^[A-Za-z]\\d[A-Za-z] \\d[A-Za-z]\\d$";
        final String ukPostalCodeRegex = "^[A-Za-z]{1,2}\\d[A-Za-z\\d]? \\d[A-Za-z]{2}$";
        final String francePostalCodeRegex = "^\\d{5}$";
        final String netherlandsPostalCodeRegex = "^\\d{4}$";
        final String japanPostalCodeRegex = "^\\d{3}-\\d{4}$";
        final String spainPostalCodeRegex = "^\\d{5}$";
        final String southAfricaPostalCodeRegex = "^\\d{4}$";
        final String germanyPostalCodeRegex = "^\\d{5}$";
        final String switzerlandPostalCodeRegex = "^\\d{4}$";
        final String brazilPostalCodeRegex = "^\\d{5}-\\d{3}$";
        final String italyPostalCodeRegex = "^\\d{5}$";
        final String usZipCodeOnlyRegex = "^\\d{5}$";

        return postalCode.matches(usZipCodeRegex) ||
                postalCode.matches(canadaPostalCodeRegex) ||
                postalCode.matches(ukPostalCodeRegex) ||
                postalCode.matches(francePostalCodeRegex) ||
                postalCode.matches(netherlandsPostalCodeRegex) ||
                postalCode.matches(japanPostalCodeRegex) ||
                postalCode.matches(spainPostalCodeRegex) ||
                postalCode.matches(southAfricaPostalCodeRegex) ||
                postalCode.matches(germanyPostalCodeRegex) ||
                postalCode.matches(switzerlandPostalCodeRegex) ||
                postalCode.matches(brazilPostalCodeRegex) ||
                postalCode.matches(italyPostalCodeRegex) ||
                postalCode.matches(usZipCodeOnlyRegex);
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isTime

    /**
     * Checks if the given string is a valid time in the format "HH:mm:ss" or "HH:mm:ss a".
     *
     * @param time the string to be checked
     * @return true if the string is a valid time, false otherwise
     * @throws IllegalArgumentException if the input value is null or empty
     */
    public static boolean isTime(String time) {
        if (time == null || time.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        return time.matches("^(?:2[0-3]|1\\d|0?\\d):[0-5]\\d(?::[0-5]\\d)?(?: [APap][Mm])?$");
    }
}