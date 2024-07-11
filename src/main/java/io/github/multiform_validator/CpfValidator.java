package io.github.multiform_validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * The CpfValidator class provides a utility method to validate CPF (Cadastro de Pessoas Físicas) numbers.
 */
public class CpfValidator {
    // Prevent instantiation
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

        final String cpfClean = cpf.replaceAll("\\D", "");

        if (cpfClean.length() != 11) {
            return false;
        }

        Pattern pattern = Pattern.compile("(\\d)\\1{10}");
        Matcher matcher = pattern.matcher(cpfClean);

        if (matcher.find()) {
            return false;
        }

        final int[] cpfArray = cpfClean.chars().map(Character::getNumericValue).toArray();

        final int sum1 = IntStream.range(0, 9).map(i -> cpfArray[i] * (10 - i)).sum();
        final int sum2 = IntStream.range(0, 10).map(i -> cpfArray[i] * (11 - i)).sum();

        final int validator1 = sum1 % 11 < 2 ? 0 : 11 - (sum1 % 11);
        final int validator2 = sum2 % 11 < 2 ? 0 : 11 - (sum2 % 11);

        return cpfArray[9] == validator1 && cpfArray[10] == validator2;
    }
}