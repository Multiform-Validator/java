package io.github.multiform_validator.utils;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class FileUtils {
  private FileUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Filters a list of strings.
   *
   * @param exclude A list of strings to exclude from the list to validate.
   * @param listToValidate The list of strings to validate.
   * @return A filtered list of strings.
   */
  public static @Nullable List<String> getFilteredList(
      List<String> exclude, List<String> listToValidate) {
    List<String> filteredList = new ArrayList<>();
    for (String item : listToValidate) {
      if (!exclude.contains(item)) {
        filteredList.add(item);
      }
    }

    if (filteredList.isEmpty()) {
      return null;
    }
    return filteredList;
  }
}
