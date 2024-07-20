package io.github.multiform_validator.identity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddressValidator {
  private AddressValidator() {}

  private static final List<Pattern> POSTAL_PATTERNS = new ArrayList<>();

  static {
    POSTAL_PATTERNS.add(Pattern.compile("^\\d{5}(-\\d{4})?$")); // US ZIP code
    POSTAL_PATTERNS.add(
        Pattern.compile("^[A-Za-z]\\d[A-Za-z] \\d[A-Za-z]\\d$")); // Canada postal code
    POSTAL_PATTERNS.add(
        Pattern.compile("^[A-Za-z]{1,2}\\d[A-Za-z\\d]? \\d[A-Za-z]{2}$")); // UK postal code
    POSTAL_PATTERNS.add(
        Pattern.compile("^\\d{5}$")); // France, Spain, Italy, Germany, US postal code
    POSTAL_PATTERNS.add(
        Pattern.compile("^\\d{4}$")); // Netherlands, South Africa, Switzerland postal code
    POSTAL_PATTERNS.add(Pattern.compile("^\\d{3}-\\d{4}$")); // Japan postal code
    POSTAL_PATTERNS.add(Pattern.compile("^\\d{5}-\\d{3}$")); // Brazil postal code
  }

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

    for (Pattern pattern : POSTAL_PATTERNS) {
      if (pattern.matcher(postalCode).matches()) {
        return true;
      }
    }

    return false;
  }
}
