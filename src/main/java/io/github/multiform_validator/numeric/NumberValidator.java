package io.github.multiform_validator.numeric;

public class NumberValidator {
  private NumberValidator() {}

  private static final String INPUT_VALUE_CANNOT_BE_EMPTY = "Input value cannot be empty";

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
}
