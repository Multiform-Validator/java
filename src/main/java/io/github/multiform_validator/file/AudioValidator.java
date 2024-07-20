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

public class AudioValidator {
  private AudioValidator() {
    throw new IllegalStateException("Utility class");
  }

  private static final Logger logger = Logger.getLogger(AudioValidator.class.getName());
  private static final String ILLEGAL_ARGUMENT_MESSAGE = "The input value cannot be null.";
  private static final String ERROR_WHILE_READING_FILE_MESSAGE =
      "An error occurred while reading the file: ";
  private static final List<String> FILE_TYPES = new ArrayList<>();

  static {
    FILE_TYPES.add("mp3");
    FILE_TYPES.add("wav");
  }

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
   * @param file The audio file to validate.
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

      List<String> filteredList = getFilteredList(exclude, FILE_TYPES);
      if (filteredList == null) {
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
    return fileBytes[0] == 0x52
        && fileBytes[1] == 0x49
        && fileBytes[2] == 0x46
        && fileBytes[3] == 0x46;
  }

  private static boolean validateAudiosFileTypes(
      byte[] fileBytes, @NotNull List<String> filteredList) {
    boolean isMp3Valid = filteredList.contains("mp3") && isMp3(fileBytes);
    boolean isWavValid = filteredList.contains("wav") && isWav(fileBytes);

    return isMp3Valid || isWavValid;
  }

  private static boolean validateAllAudiosFileTypes(byte[] fileBytes) {
    return isMp3(fileBytes) || isWav(fileBytes);
  }
}
