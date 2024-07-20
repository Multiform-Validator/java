package io.github.multiform_validator.format;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DateValidator {
  private DateValidator() {}

  private static final String INPUT_VALUE_CANNOT_BE_EMPTY = "Input value cannot be empty.";

  private static final List<DateTimeFormatter> DATE_FORMATTERS =
      Arrays.asList(
          DateTimeFormatter.ofPattern("yyyy-MM-dd"),
          DateTimeFormatter.ofPattern("MM/dd/yyyy"),
          DateTimeFormatter.ofPattern("dd-MM-yyyy"),
          DateTimeFormatter.ofPattern("yyyy/MM/dd"),
          DateTimeFormatter.ofPattern("dd.MM.yyyy"),
          DateTimeFormatter.ofPattern("yyyy.MM.dd"),
          DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH),
          DateTimeFormatter.ofPattern("dd-MMMM-yyyy", Locale.ENGLISH),
          DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH),
          DateTimeFormatter.ofPattern("dd-MMMM-yy", Locale.ENGLISH));

  private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS =
      Arrays.asList(
          DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
          DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
          DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
          DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"),
          DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"),
          DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"),
          DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH),
          DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss", Locale.ENGLISH),
          DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss", Locale.ENGLISH),
          DateTimeFormatter.ofPattern("dd-MMMM-yy HH:mm:ss", Locale.ENGLISH));

  /**
   * Checks if the given string is a valid date. The date can be in the following formats:
   *
   * @param dateStr the string to be checked
   * @return true if the string is a valid date, false otherwise
   */
  public static boolean isDate(String dateStr) {
    if (dateStr == null || dateStr.isEmpty()) {
      throw new IllegalArgumentException("Date string cannot be null or empty");
    }

    for (DateTimeFormatter formatter : DATE_FORMATTERS) {
      if (isValidFormat(dateStr, formatter, false)) {
        return true;
      }
    }

    for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
      if (isValidFormat(dateStr, formatter, true)) {
        return true;
      }
    }

    return false;
  }

  private static boolean isValidFormat(
      String dateStr, DateTimeFormatter formatter, boolean isDateTime) {
    try {
      if (isDateTime) {
        LocalDateTime.parse(dateStr, formatter);
      } else {
        LocalDate.parse(dateStr, formatter);
      }
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  /**
   * Checks if the given string is a valid time in the format "HH:mm:ss" or "HH:mm:ss a".
   *
   * @param time the string to be checked
   * @return true if the string is a valid time, false otherwise
   * @throws IllegalArgumentException if the input value is null or empty
   */
  public static boolean isTime(String time) {
    if (time == null || time.isEmpty()) {
      throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
    }

    return time.matches("^(?:2[0-3]|1\\d|0?\\d):[0-5]\\d(?::[0-5]\\d)?(?: [APap][Mm])?$");
  }
}
