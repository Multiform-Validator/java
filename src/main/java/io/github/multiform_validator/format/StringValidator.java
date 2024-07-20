package io.github.multiform_validator.format;

public class StringValidator {

  private StringValidator() {}

  private static final String INPUT_VALUE_CANNOT_BE_EMPTY = "Input value cannot be empty";

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
}
