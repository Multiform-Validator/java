package io.github.multiform_validator.file;

import static io.github.multiform_validator.utils.FileUtils.getFilteredList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ImageValidator {
  private ImageValidator() {
    throw new IllegalStateException("Utility class");
  }

  private static final Logger logger = Logger.getLogger(ImageValidator.class.getName());
  private static final String ILLEGAL_ARGUMENT_MESSAGE = "The input value cannot be null.";
  private static final String ERROR_WHILE_READING_FILE_MESSAGE =
      "An error occurred while reading the file: ";
  private static final List<String> FILE_TYPES = new ArrayList<>();

  static {
    FILE_TYPES.add("gif");
    FILE_TYPES.add("ico");
    FILE_TYPES.add("jpeg");
    FILE_TYPES.add("png");
  }

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
   * @param file The image file to validate.
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

      List<String> filteredList = getFilteredList(exclude, FILE_TYPES);
      if (filteredList == null) {
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
    return fileBytes[0] == (byte) 0x89
        && fileBytes[1] == 0x50
        && fileBytes[2] == 0x4E
        && fileBytes[3] == 0x47;
  }

  @Contract(pure = true)
  private static boolean isJpeg(byte @NotNull [] fileBytes) {
    return fileBytes[0] == (byte) 0xFF
        && fileBytes[1] == (byte) 0xD8
        && fileBytes[2] == (byte) 0xFF;
  }

  @Contract(pure = true)
  private static boolean isGif(byte @NotNull [] fileBytes) {
    return fileBytes[0] == 0x47
        && fileBytes[1] == 0x49
        && fileBytes[2] == 0x46
        && fileBytes[3] == 0x38;
  }

  @Contract(pure = true)
  private static boolean isIco(byte @NotNull [] fileBytes) {
    return fileBytes[0] == 0x00 && fileBytes[1] == 0x00 && fileBytes[2] == 0x01;
  }

  private static boolean validateImagesFileTypes(
      byte[] fileBytes, @NotNull List<String> filteredList) {
    boolean isGifValid = filteredList.contains("gif") && isGif(fileBytes);
    boolean isIcoValid = filteredList.contains("ico") && isIco(fileBytes);
    boolean isJpegValid = filteredList.contains("jpeg") && isJpeg(fileBytes);
    boolean isPngValid = filteredList.contains("png") && isPng(fileBytes);

    return isGifValid || isIcoValid || isJpegValid || isPngValid;
  }

  private static boolean validateAllImagesFileTypes(byte[] fileBytes) {
    return isGif(fileBytes) || isIco(fileBytes) || isJpeg(fileBytes) || isPng(fileBytes);
  }
}
