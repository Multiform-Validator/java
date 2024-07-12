package io.github.multiform_validator;

public class CreditCardValidator {
    // Message constants
    private static final String INPUT_VALUE_CANNOT_BE_EMPTY = "Input value cannot be empty.";

    // Prevent instantiation
    private CreditCardValidator() {
        throw new IllegalStateException("Utility class");
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // isCreditCardValid

    /**
     * Validates a credit card number.
     *
     * @param creditCard The credit card number to validate.
     * @return true if the credit card number is valid, false otherwise.
     * @throws IllegalArgumentException if the input value is null or empty.
     */
    public static boolean isCreditCardValid(String creditCard) {
        if (creditCard == null || creditCard.isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
        }

        final String creditCardString = creditCard.replaceAll("\\D", "");

        if (creditCardString.length() < 13 || creditCardString.length() > 19) {
            return false;
        }

        int sum = 0;
        boolean alternate = false;

        for (int i = creditCardString.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(creditCardString.substring(i, i + 1));

            if (alternate) {
                n *= 2;

                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }

    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // identifyFlagCard

    /**
     * Identifies the flag of a credit card based on its number.
     *
     * @param cardNumber The credit card number.
     * @return The flag of the credit card, or "Unknown" if the flag is not recognized.
     * @throws IllegalArgumentException if the input value is null or empty.
     */
    public static String identifyFlagCard(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new IllegalArgumentException("The input should be a string.");
        }

        String[][] flags = {
                {"Visa", "^4[0-9]{12}(?:[0-9]{3})?$"},
                {"Mastercard", "^5[1-5][0-9]{14}$"},
                {"American Express", "^3[47][0-9]{13}$"},
                {"Discover", "^6(?:011|5[0-9]{2})[0-9]{12}$"},
                {"JCB", "^(?:2131|1800|35[0-9]{3})[0-9]{11}$"},
                {"Diners Club", "^3(?:0[0-5]|[68][0-9])[0-9]{11}$"},
                {"Maestro", "^(?:5[0678][0-9]{2}|6304|6390|67[0-9]{2})[0-9]{12,15}$"},
                {"UnionPay", "^(62|88)[0-9]{14,17}$"},
                {"Elo", "^63[789][0-9]{13}$"},
                {"Hipercard", "^(3841[0-9]{12}|60[0-9]{14})$"}
        };

        for (String[] flag : flags) {
            if (cardNumber.matches(flag[1])) {
                return flag[0];
            }
        }

        return "Unknown";
    }

}