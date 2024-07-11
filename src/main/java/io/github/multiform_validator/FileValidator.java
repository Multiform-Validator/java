package io.github.multiform_validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileValidator {
    // Prevent instantiation
    private FileValidator() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = Logger.getLogger(FileValidator.class.getName());

    /**
     * Validates an image file.
     *
     * @param file The image file to validate.
     * @return true if the file is a valid image, false otherwise.
     * @throws IllegalArgumentException if the input value is null.
     */
    public static boolean validateImage(File file) {
        return validateImage(file, null);
    }

    /**
     * Validates an image file, excluding specific file types.
     *
     * @param file    The image file to validate.
     * @param exclude A list of file types to exclude from validation.
     * @return true if the file is a valid image, false otherwise.
     * @throws IllegalArgumentException if the input value is null.
     */
    public static boolean validateImage(File file, List<String> exclude) {
        if (file == null) {
            throw new IllegalArgumentException("The input value cannot be null.");
        }

        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());

            if (exclude == null) {
                return isGif(fileBytes) || isIco(fileBytes) || isJpeg(fileBytes) || isPng(fileBytes);
            }

            List<String> listToValidate = new ArrayList<>();
            listToValidate.add("gif");
            listToValidate.add("ico");
            listToValidate.add("jpeg");
            listToValidate.add("png");

            List<String> filteredList = new ArrayList<>();
            for (String item : listToValidate) {
                if (!exclude.contains(item)) {
                    filteredList.add(item);
                }
            }

            if (filteredList.isEmpty()) {
                return false;
            }

            return validateFileTypes(fileBytes, filteredList);

        } catch (IOException e) {
            logger.severe("An error occurred while reading the file: " + e.getMessage());
            return false;
        }
    }

    private static boolean isPng(byte[] fileBytes) {
        return fileBytes[0] == (byte) 0x89 && fileBytes[1] == 0x50 && fileBytes[2] == 0x4E && fileBytes[3] == 0x47;
    }

    private static boolean isJpeg(byte[] fileBytes) {
        return fileBytes[0] == (byte) 0xFF && fileBytes[1] == (byte) 0xD8 && fileBytes[2] == (byte) 0xFF;
    }

    private static boolean isGif(byte[] fileBytes) {
        return fileBytes[0] == 0x47 && fileBytes[1] == 0x49 && fileBytes[2] == 0x46 && fileBytes[3] == 0x38;
    }

    private static boolean isIco(byte[] fileBytes) {
        return fileBytes[0] == 0x00 && fileBytes[1] == 0x00 && fileBytes[2] == 0x01;
    }

    private static boolean validateFileTypes(byte[] fileBytes, List<String> filteredList) {
        boolean isGifValid = filteredList.contains("gif") && isGif(fileBytes);
        boolean isIcoValid = filteredList.contains("ico") && isIco(fileBytes);
        boolean isJpegValid = filteredList.contains("jpeg") && isJpeg(fileBytes);
        boolean isPngValid = filteredList.contains("png") && isPng(fileBytes);

        return isGifValid || isIcoValid || isJpegValid || isPngValid;
    }
}