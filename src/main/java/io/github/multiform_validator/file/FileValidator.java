package io.github.multiform_validator.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class FileValidator {
  private FileValidator() {
    throw new IllegalStateException("Utility class");
  }

  private static final Logger logger = Logger.getLogger(FileValidator.class.getName());
  private static final String ILLEGAL_ARGUMENT_MESSAGE = "The input value cannot be null.";
  private static final String ERROR_WHILE_READING_FILE_MESSAGE =
      "An error occurred while reading the file: ";

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
    return fileBytes[0] == 0x25
        && fileBytes[1] == 0x50
        && fileBytes[2] == 0x44
        && fileBytes[3] == 0x46;
  }

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
      if ((b < 0x20 || b > 0x7e) && b != 0x0a && b != 0x0d) {
        return false;
      }
    }
    return true;
  }
}
