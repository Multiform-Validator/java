package io.github.multiform_validator.identity;

import static io.github.multiform_validator.utils.NumberUtils.NON_DIGIT_PATTERN;
import static io.github.multiform_validator.utils.NumberUtils.isAllSameDigit;

import org.jetbrains.annotations.NotNull;

/**
 * The CpfValidator class provides a utility method to validate CPF (Cadastro de Pessoas Físicas)
 * numbers.
 */
public class CpfValidator {
  private CpfValidator() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Validates a CPF number.
   *
   * @param cpf the CPF number to validate
   * @return true if the CPF number is valid, false otherwise
   * @throws NullPointerException if the CPF number is null
   */
  public static boolean cpfIsValid(String cpf) {
    if (cpf == null) {
      throw new NullPointerException("CPF cannot be null");
    }

    final String cpfClean = NON_DIGIT_PATTERN.matcher(cpf).replaceAll("");
    if (cpfClean.length() != 11 || isAllSameDigit(cpfClean)) {
      return false;
    }

    int[] cpfArray = getCpfArray(cpfClean);

    int sum1 = 0;
    int sum2 = 0;
    for (int i = 0; i < 9; i++) {
      sum1 += cpfArray[i] * (10 - i);
      sum2 += cpfArray[i] * (11 - i);
    }
    sum2 += cpfArray[9] * 2;

    int validator1 = sum1 % 11 < 2 ? 0 : 11 - (sum1 % 11);
    int validator2 = sum2 % 11 < 2 ? 0 : 11 - (sum2 % 11);

    return cpfArray[9] == validator1 && cpfArray[10] == validator2;
  }

  /**
   * Converts a clean CPF number to an array of integers.
   *
   * @param cpfClean the clean CPF number
   * @return an array of integers
   */
  private static int @NotNull [] getCpfArray(String cpfClean) {
    int[] cpfArray = new int[11];
    for (int i = 0; i < 11; i++) {
      cpfArray[i] = cpfClean.charAt(i) - '0';
    }
    return cpfArray;
  }
}
