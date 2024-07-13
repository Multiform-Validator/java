package io.github.multiform_validator;

import java.util.Arrays;

public class CnpjValidator {
    // Prevent instantiation
    private CnpjValidator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Calculate the first verifier digit of a CNPJ
     *
     * @param cnpjBase an array of integers with the first 12 digits of a CNPJ
     * @return the first verifier digit
     */
    private static int calculateFirstVerifier(int[] cnpjBase) {
        final int[] weight = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;

        for (int i = 0; i < 12; i++) {
            sum += cnpjBase[i] * weight[i];
        }
        final int remainder = sum % 11;

        return remainder < 2 ? 0 : 11 - remainder;
    }

    /**
     * Calculate the second verifier digit of a CNPJ
     *
     * @param cnpjBase      an array of integers with the first 12 digits of a CNPJ
     * @param firstVerifier the first verifier digit
     * @return the second verifier digit
     */
    private static int calculateSecondVerifier(int[] cnpjBase, int firstVerifier) {
        final int[] weight = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;

        for (int i = 0; i < 12; i++) {
            sum += cnpjBase[i] * weight[i];
        }
        sum += firstVerifier * weight[12];

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

        final String cnpjClean = cnpj.replaceAll("\\D", "");

        if (cnpjClean.isEmpty()) {
            return false;
        }

        if (cnpjClean.length() != 14) {
            return false;
        }

        // Check if all digits are the same
        if (cnpjClean.chars().distinct().count() <= 1) {
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