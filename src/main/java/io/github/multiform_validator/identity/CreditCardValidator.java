package io.github.multiform_validator.identity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class CreditCardValidator {
  private CreditCardValidator() {
    throw new IllegalStateException("Utility class");
  }

  private static final String INPUT_VALUE_CANNOT_BE_EMPTY = "Input value cannot be empty.";
  private static final Map<Pattern, String> patternFlagMap = new HashMap<>();

  static {
    patternFlagMap.put(Pattern.compile("^4\\d{12}(?:\\d{3})?$"), "Visa");
    patternFlagMap.put(Pattern.compile("^5[1-5]\\d{14}$"), "Mastercard");
    patternFlagMap.put(Pattern.compile("^3[47]\\d{13}$"), "American Express");
    patternFlagMap.put(Pattern.compile("^6(?:011|5\\d{2})\\d{12}$"), "Discover");
    patternFlagMap.put(Pattern.compile("^(?:2131|1800|35\\d{3})\\d{11}$"), "JCB");
    patternFlagMap.put(Pattern.compile("^3(?:0[0-5]|[68]\\d)\\d{11}$"), "Diners Club");
    patternFlagMap.put(
        Pattern.compile("^(?:5[0678]\\d{2}|6304|6390|67\\d{2})\\d{12,15}$"), "Maestro");
    patternFlagMap.put(Pattern.compile("^(62|88)\\d{14,17}$"), "UnionPay");
    patternFlagMap.put(Pattern.compile("^63[789]\\d{13}$"), "Elo");
    patternFlagMap.put(Pattern.compile("^(3841\\d{12}|60(?!11)\\d{14})$"), "Hipercard");
  }

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

  /**
   * Identifies the flag of a credit card based on its number.
   *
   * @param cardNumber The credit card number.
   * @return An optional containing the flag of the credit card if it was identified, or an empty
   *     optional otherwise.
   */
  public static Optional<String> identifyFlagCard(String cardNumber) {
    if (cardNumber == null || cardNumber.isEmpty()) {
      return Optional.empty();
    }

    for (Map.Entry<Pattern, String> entry : patternFlagMap.entrySet()) {
      if (entry.getKey().matcher(cardNumber).matches()) {
        return Optional.of(entry.getValue());
      }
    }

    return Optional.empty();
  }
}
