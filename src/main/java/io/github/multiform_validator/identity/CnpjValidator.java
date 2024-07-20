package io.github.multiform_validator.identity;

import static io.github.multiform_validator.utils.NumberUtils.NON_DIGIT_PATTERN;
import static io.github.multiform_validator.utils.NumberUtils.isAllSameDigit;

import java.util.Arrays;

public class CnpjValidator {

  private CnpjValidator() {
    throw new IllegalStateException("Utility class");
  }

  private static final int[] FIRST_WEIGHT = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
  private static final int[] SECOND_WEIGHT = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

  /**
   * Calculate the first verifier digit of a CNPJ
   *
   * @param cnpjBase an array of integers with the first 12 digits of a CNPJ
   * @return the first verifier digit
   */
  private static int calculateFirstVerifier(int[] cnpjBase) {
    int sum = 0;
    for (int i = 0; i < 12; i++) {
      int i1 = cnpjBase[i] * FIRST_WEIGHT[i];
      sum += i1;
    }
    final int remainder = sum % 11;

    return remainder < 2 ? 0 : 11 - remainder;
  }

  /**
   * Calculate the second verifier digit of a CNPJ
   *
   * @param cnpjBase an array of integers with the first 12 digits of a CNPJ
   * @param firstVerifier the first verifier digit
   * @return the second verifier digit
   */
  private static int calculateSecondVerifier(int[] cnpjBase, int firstVerifier) {
    int sum = 0;
    for (int i = 0; i < 12; i++) {
      int i1 = cnpjBase[i] * SECOND_WEIGHT[i];
      sum += i1;
    }
    sum += firstVerifier * SECOND_WEIGHT[12];

    final int remainder = sum % 11;

    return remainder < 2 ? 0 : 11 - remainder;
  }

  /**
   * Check if a CNPJ is valid
   *
   * @param cnpj the CNPJ to be validated
   * @return true if the CNPJ is valid, false otherwise
   * @throws NullPointerException if the CNPJ is null
   */
  public static boolean cnpjIsValid(String cnpj) {
    if (cnpj == null) {
      throw new NullPointerException("CNPJ cannot be null");
    }

    final String cnpjClean = NON_DIGIT_PATTERN.matcher(cnpj).replaceAll("");

    if (cnpjClean.length() != 14 || isAllSameDigit(cnpjClean)) {
      return false;
    }

    // Convert the string to an array of integers
    final int[] cnpjArray = cnpjClean.chars().map(Character::getNumericValue).toArray();

    // Calculate the first verifier and second verifier
    final int[] cnpjBase = Arrays.copyOfRange(cnpjArray, 0, 12);
    final int firstVerifier = calculateFirstVerifier(cnpjBase);

    final int[] cnpjBaseWithFirstVerifier = Arrays.copyOf(cnpjBase, cnpjBase.length + 1);
    cnpjBaseWithFirstVerifier[cnpjBaseWithFirstVerifier.length - 1] = firstVerifier;

    final int secondVerifier = calculateSecondVerifier(cnpjBaseWithFirstVerifier, firstVerifier);

    return cnpjArray[12] == firstVerifier && cnpjArray[13] == secondVerifier;
  }
}
