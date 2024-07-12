package io.github.multiform_validator;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

    // Logger
    private static final Logger logger = Logger.getLogger(FileValidator.class.getName());

    // Constants
    private static final String ILLEGAL_ARGUMENT_MESSAGE = "The input value cannot be null.";
    private static final String ERROR_WHILE_READING_FILE_MESSAGE = "An error occurred while reading the file: ";

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // Image file validation

    /**
     * Validates an image file.
     *
     * @param file The image file to validate.
     * @return true if the file is a valid image, false otherwise.
     * @throws IllegalArgumentException if the input value is null.
     */
    public static boolean isValidImage(File file) {
        return isValidImage(file, null);
    }

    /**
     * Validates an image file, excluding specific file types.
     *
     * @param file    The image file to validate.
     * @param exclude A list of file types to exclude from validation.
     * @return true if the file is a valid image, false otherwise.
     * @throws IllegalArgumentException if the input value is null.
     */
    public static boolean isValidImage(File file, List<String> exclude) {
        if (file == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());

            if (exclude == null) {
                return validateAllImagesFileTypes(fileBytes);
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

            return validateImagesFileTypes(fileBytes, filteredList);

        } catch (IOException e) {
            logger.severe(ERROR_WHILE_READING_FILE_MESSAGE + e.getMessage());
            return false;
        }
    }

    @Contract(pure = true)
    private static boolean isPng(byte @NotNull [] fileBytes) {
        return fileBytes[0] == (byte) 0x89 && fileBytes[1] == 0x50 && fileBytes[2] == 0x4E && fileBytes[3] == 0x47;
    }

    @Contract(pure = true)
    private static boolean isJpeg(byte @NotNull [] fileBytes) {
        return fileBytes[0] == (byte) 0xFF && fileBytes[1] == (byte) 0xD8 && fileBytes[2] == (byte) 0xFF;
    }

    @Contract(pure = true)
    private static boolean isGif(byte @NotNull [] fileBytes) {
        return fileBytes[0] == 0x47 && fileBytes[1] == 0x49 && fileBytes[2] == 0x46 && fileBytes[3] == 0x38;
    }

    @Contract(pure = true)
    private static boolean isIco(byte @NotNull [] fileBytes) {
        return fileBytes[0] == 0x00 && fileBytes[1] == 0x00 && fileBytes[2] == 0x01;
    }

    private static boolean validateImagesFileTypes(byte[] fileBytes, @NotNull List<String> filteredList) {
        boolean isGifValid = filteredList.contains("gif") && isGif(fileBytes);
        boolean isIcoValid = filteredList.contains("ico") && isIco(fileBytes);
        boolean isJpegValid = filteredList.contains("jpeg") && isJpeg(fileBytes);
        boolean isPngValid = filteredList.contains("png") && isPng(fileBytes);

        return isGifValid || isIcoValid || isJpegValid || isPngValid;
    }

    private static boolean validateAllImagesFileTypes(byte[] fileBytes) {
        return isGif(fileBytes) || isIco(fileBytes) || isJpeg(fileBytes) || isPng(fileBytes);
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // Audio file validation

    /**
     * Validates an audio file.
     *
     * @param file The audio file to validate.
     * @return true if the file is a valid audio, false otherwise.
     * @throws IllegalArgumentException if the input value is null.
     */
    public static boolean isValidAudio(File file) {
        return isValidAudio(file, null);

    }

    /**
     * Validates an audio file, excluding specific file types.
     *
     * @param file    The audio file to validate.
     * @param exclude A list of file types to exclude from validation.
     * @return true if the file is a valid audio, false otherwise.
     * @throws IllegalArgumentException if the input value is null.
     */
    public static boolean isValidAudio(File file, List<String> exclude) {
        if (file == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());

            if (exclude == null) {
                return validateAllAudiosFileTypes(fileBytes);
            }

            List<String> listToValidate = new ArrayList<>();
            listToValidate.add("mp3");
            listToValidate.add("wav");

            List<String> filteredList = new ArrayList<>();
            for (String item : listToValidate) {
                if (!exclude.contains(item)) {
                    filteredList.add(item);
                }
            }

            if (filteredList.isEmpty()) {
                return false;
            }

            return validateAudiosFileTypes(fileBytes, filteredList);

        } catch (IOException e) {
            logger.severe(ERROR_WHILE_READING_FILE_MESSAGE + e.getMessage());
            return false;
        }
    }


    @Contract(pure = true)
    private static boolean isMp3(byte @NotNull [] fileBytes) {
        return fileBytes[0] == 0x49 && fileBytes[1] == 0x44 && fileBytes[2] == 0x33;
    }

    @Contract(pure = true)
    private static boolean isWav(byte @NotNull [] fileBytes) {
        return fileBytes[0] == 0x52 && fileBytes[1] == 0x49 && fileBytes[2] == 0x46 && fileBytes[3] == 0x46;
    }

    private static boolean validateAudiosFileTypes(byte[] fileBytes, @NotNull List<String> filteredList) {
        boolean isMp3Valid = filteredList.contains("mp3") && isMp3(fileBytes);
        boolean isWavValid = filteredList.contains("wav") && isWav(fileBytes);

        return isMp3Valid || isWavValid;
    }

    private static boolean validateAllAudiosFileTypes(byte[] fileBytes) {
        return isMp3(fileBytes) || isWav(fileBytes);
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // Pdf file validation

    /**
     * Validates a PDF file.
     *
     * @param file The PDF file to validate.
     * @return true if the file is a valid PDF, false otherwise.
     * @throws IllegalArgumentException if the input value is null.
     */
    public static boolean isValidPdf(File file) {
        if (file == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            return isPdf(fileBytes);
        } catch (IOException e) {
            logger.severe(ERROR_WHILE_READING_FILE_MESSAGE + e.getMessage());
            return false;
        }
    }

    @Contract(pure = true)
    private static boolean isPdf(byte @NotNull [] fileBytes) {
        return fileBytes[0] == 0x25 && fileBytes[1] == 0x50 && fileBytes[2] == 0x44 && fileBytes[3] == 0x46;
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // Txt file validation

    /**
     * Validates a TXT file.
     *
     * @param file The TXT file to validate.
     * @return true if the file is a valid TXT, false otherwise.
     * @throws IllegalArgumentException if the input value is null.
     */
    public static boolean isValidTxt(File file) {
        if (file == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            return isTxt(fileBytes);
        } catch (IOException e) {
            logger.severe(ERROR_WHILE_READING_FILE_MESSAGE + e.getMessage());
            return false;
        }
    }

    @Contract(pure = true)
    private static boolean isTxt(byte @NotNull [] fileBytes) {
        if (fileBytes.length == 0) {
            return false;
        }
        for (byte b : fileBytes) {
            if (
                    (b < 0x20 || b > 0x7e) &&
                            b != 0x0a &&
                            b != 0x0d
            ) {
                return false;
            }
        }
        return true;
    }
}