package io.github.multiform_validator.numeric;

public class MachineValidator {
  private MachineValidator() {}

  private static final String INPUT_VALUE_CANNOT_BE_EMPTY = "Input value cannot be empty";

  /**
   * Checks if the given string is a valid MAC address.
   *
   * @param macAddress the string to be checked
   * @return true if the string is a valid MAC address, false otherwise
   * @throws IllegalArgumentException if the input value is null or empty
   */
  public static boolean isMACAddress(String macAddress) {
    if (macAddress == null || macAddress.isEmpty()) {
      throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
    }

    return macAddress.matches("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
  }

  /**
   * Checks if the given port number is valid.
   *
   * @param port the port number to be checked
   * @return true if the port number is valid, false otherwise
   */
  public static boolean isPort(int port) {
    return port >= 0 && port <= 65535;
  }

  /**
   * Checks if the given string is a valid port number.
   *
   * @param port the string to be checked
   * @return true if the string is a valid port number, false otherwise
   * @throws IllegalArgumentException if the input value is null or empty
   */
  public static boolean isPort(String port) {
    if (port == null || port.isEmpty()) {
      throw new IllegalArgumentException(INPUT_VALUE_CANNOT_BE_EMPTY);
    }

    try {
      final int portNumber = Integer.parseInt(port);
      return portNumber >= 0 && portNumber <= 65535;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
