package io.github.multiform_validator.utils;

import java.util.regex.Pattern;

public class NumberUtils {
  private NumberUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static final Pattern NON_DIGIT_PATTERN = Pattern.compile("\\D");

  /**
   * Check if all characters in a string are the same.
   *
   * @param string The string to check.
   * @return true if all characters are the same, false otherwise.
   */
  public static boolean isAllSameDigit(String string) {
    char firstChar = string.charAt(0);
    for (int i = 1; i < string.length(); i++) {
      if (string.charAt(i) != firstChar) {
        return false;
      }
    }
    return true;
  }
}
