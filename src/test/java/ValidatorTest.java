import static io.github.multiform_validator.format.DateValidator.isDate;
import static io.github.multiform_validator.format.DateValidator.isTime;
import static io.github.multiform_validator.format.EmailValidator.isEmail;
import static io.github.multiform_validator.format.StringValidator.*;
import static io.github.multiform_validator.identity.AddressValidator.isCEP;
import static io.github.multiform_validator.identity.AddressValidator.isPostalCode;
import static io.github.multiform_validator.numeric.MachineValidator.isMACAddress;
import static io.github.multiform_validator.numeric.MachineValidator.isPort;
import static io.github.multiform_validator.numeric.NumberValidator.isDecimal;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidatorTest {

  @Test
  void testIsAscii() {
    assertTrue(isAscii("Hello World"));
    assertTrue(isAscii("0123456789"));
    assertTrue(isAscii("abcdefghijklmnopqrstuvwxyz"));
    assertTrue(isAscii("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    assertTrue(isAscii("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"));
    assertFalse(isAscii("こんにちは"));
    assertFalse(isAscii("你好"));
    assertFalse(isAscii("안녕하세요"));
    assertFalse(isAscii("مرحبا"));
    assertFalse(isAscii("नमस्ते"));
    assertFalse(isAscii("สวัสดี"));
    assertThrows(IllegalArgumentException.class, () -> isAscii(null));
    assertThrows(IllegalArgumentException.class, () -> isAscii(""));
  }

  @Test
  void testIsBase64() {
    assertTrue(isBase64("SGVsbG8gV29ybGQ="));
    assertTrue(isBase64("MTIzNDU2Nzg5"));
    assertTrue(isBase64("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo="));
    assertTrue(isBase64("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVo="));
    assertFalse(isBase64("Hello World"));
    assertFalse(isBase64("こんにちは"));
    assertFalse(isBase64("你好"));
    assertFalse(isBase64("안녕하세요"));
    assertFalse(isBase64("مرحبا"));
    assertThrows(IllegalArgumentException.class, () -> isBase64(null));
    assertThrows(IllegalArgumentException.class, () -> isBase64(""));
  }

  @Test
  void testIsCEP() {
    assertTrue(isCEP("12345678"));
    assertFalse(isCEP("1234567890"));
    assertFalse(isCEP("abcdefgh"));
    assertFalse(isCEP("1234567"));
    assertTrue(isCEP("12345-678"));
  }

  @Test
  void testIsDateTrue() {
    // Valid date formats
    assertTrue(isDate("2022-01-01")); // ISO 8601 (YYYY-MM-DD)
    assertTrue(isDate("01/01/2022")); // Common US format (MM/DD/YYYY)
    assertTrue(isDate("01-01-2022")); // Common EU format (DD-MM-YYYY)
    assertTrue(isDate("2022/01/01")); // ISO alternate (YYYY/MM/DD)
    assertTrue(isDate("01.01.2022")); // Format with dots (DD.MM.YYYY)
    assertTrue(isDate("2022.01.01")); // Format with dots (YYYY.MM.DD)
    assertTrue(isDate("01-Jan-2022")); // Format with abbreviated month (DD-MMM-YYYY)
    assertTrue(isDate("01-January-2022")); // Format with full month name (DD-Month-YYYY)
    assertTrue(isDate("01-Jan-22")); // Format with abbreviated month and two-digit year (DD-MMM-YY)
    assertTrue(
        isDate("01-January-22")); // Format with full month name and two-digit year (DD-Month-YY)
  }

  @Test
  void testIsDateWithNumbersTrue() {
    // Valid date-time formats
    assertTrue(isDate("2022-01-01T10:15:30")); // ISO 8601 (YYYY-MM-DDTHH:MM:SS)
    assertTrue(isDate("2022-01-01 10:15:30")); // Space-separated date and time
    assertTrue(isDate("2022/01/01 10:15:30")); // Slash-separated date and time
    assertTrue(isDate("01-01-2022 10:15:30")); // Hyphen-separated date and time (DD-MM-YYYY)
    assertTrue(isDate("01.01.2022 10:15:30")); // Dot-separated date and time (DD.MM.YYYY)
    assertTrue(isDate("2022.01.01 10:15:30")); // Dot-separated date and time (YYYY.MM.DD)
    assertTrue(isDate("01-Jan-2022 10:15:30")); // Abbreviated month date and time (DD-MMM-YYYY)
    assertTrue(isDate("01-January-2022 10:15:30")); // Full month name date and time (DD-Month-YYYY)
    assertTrue(isDate("01-Jan-22 10:15:30")); // Abbreviated month and two-digit year date and time
    // (DD-MMM-YY)
    assertTrue(
        isDate("01-January-22 10:15:30")); // Full month name and two-digit year date and time
    // (DD-Month-YY)
  }

  @Test
  void testIsDateWithNumbersFalse() {
    // Invalid date-time formats
    assertFalse(isDate("2022-13-01T10:15:30")); // Invalid month
    assertFalse(isDate("2022-01-32T10:15:30")); // Invalid day
    assertFalse(isDate("01-01-22T10:15:30")); // Invalid year format
    assertFalse(isDate("2022-01-01T25:15:30")); // Invalid hour
    assertFalse(isDate("2022-01-01T10:60:30")); // Invalid minute
    assertFalse(isDate("2022-01-01T10:15:60")); // Invalid second
    assertFalse(isDate("01-01-22 25:15:30")); // Invalid hour
    assertFalse(isDate("01-01-22 10:60:30")); // Invalid minute
    assertFalse(isDate("01-01-22 10:15:60")); // Invalid second

    // Null and empty string
    assertThrows(IllegalArgumentException.class, () -> isDate(null));
    assertThrows(IllegalArgumentException.class, () -> isDate(""));
  }

  @Test
  void testIsDateFalse() {
    // Invalid date formats
    assertFalse(isDate("2022-13-01")); // Invalid month
    assertFalse(isDate("2022-01-32")); // Invalid day
    assertFalse(isDate("01-01-22")); // Invalid year format
    assertFalse(isDate("abc-def-ghi")); // Invalid characters
    assertFalse(isDate("01-01-22 10:15:30")); // Invalid format
    assertFalse(isDate("01-01-22abc")); // Invalid format

    // Null and empty string
    assertThrows(IllegalArgumentException.class, () -> isDate(null));
    assertThrows(IllegalArgumentException.class, () -> isDate(""));
  }

  @Test
  void testIsDecimal() {
    assertTrue(isDecimal("3.14"));
    assertFalse(isDecimal("42"));
    assertThrows(IllegalArgumentException.class, () -> isDecimal(null));
    assertThrows(IllegalArgumentException.class, () -> isDecimal(""));
  }

  @Test
  void testValidEmail() {
    assertTrue(isEmail("foo@bar.com"));
    assertTrue(isEmail("Foo@Bar.com"));
    assertTrue(isEmail("foo@bar.com.br"));
  }

  @Test
  void testInvalidEmail1() {
    assertFalse(isEmail("foo@bar"));
    assertFalse(isEmail("1foo@bar.com"));
    assertFalse(isEmail("foo@1bar.com"));
    assertFalse(isEmail("foo@bar.1com"));
    assertFalse(isEmail("foo.bar.com"));
    assertFalse(isEmail("joaoaoao@gmail.com.com"));
    assertFalse(isEmail("joao..@gmail.com"));
    assertFalse(isEmail("joao.@gmail.com"));
    assertFalse(isEmail("joao@@gmail.com"));
    assertFalse(isEmail("joao@gmail..com"));
    assertFalse(isEmail(".foo@bar.com"));
    assertFalse(isEmail(",foo@bar.com"));
    assertFalse(isEmail("!foo@bar.com"));
    assertFalse(isEmail("@foo@bar.com"));
    assertFalse(isEmail("#foo@bar.com"));
    assertFalse(isEmail("$foo@bar.com"));
    assertFalse(isEmail("foo@@bar.com"));
    assertFalse(isEmail("foo@bar.com.br.br"));
    assertFalse(isEmail("foo@bar.com.com.br.br"));
    assertFalse(isEmail("foo@bar.com.com.br"));
  }

  @Test
  void testInvalidEmail2() {
    assertFalse(isEmail("foo!@bar.com"));
    assertFalse(isEmail("foo bar@baz.com"));
    assertFalse(isEmail(" foo@bar.com"));
    assertFalse(isEmail("foo@bar.com "));
    assertFalse(isEmail("foo..bar@baz.com"));
    assertFalse(isEmail("foo@@bar.com"));
    assertFalse(isEmail("foo@bar..com"));
    assertFalse(isEmail("foo..bar@baz.com"));
    assertFalse(isEmail("foo@bar.com.."));
    assertFalse(isEmail("foo@bar..com"));
    assertFalse(isEmail("foo..@bar.com"));
    assertFalse(isEmail("foo@bar..com."));
    assertFalse(isEmail("foo..@bar..com."));
    assertFalse(isEmail("foo@bar.com..."));
    assertFalse(isEmail("foo@bar!com"));
    assertFalse(isEmail("foo!@bar.com"));
    assertFalse(isEmail("foo@bar.c"));
    assertFalse(isEmail("foo@bar."));
    assertFalse(isEmail("foo@bar"));
    assertFalse(isEmail("foo@bar."));
    assertFalse(isEmail("foo@bar.."));
    assertFalse(isEmail("foo@bar..."));
  }

  @Test
  void testEmptyEmail() {
    assertFalse(isEmail(""));
  }

  @Test
  void testNullEmail() {
    assertThrows(NullPointerException.class, () -> isEmail(null));
  }

  @Test
  void testIsMACAddress() {
    assertTrue(isMACAddress("00:1B:44:11:3A:B7"));
    assertFalse(isMACAddress("Hello World"));
    assertFalse(isMACAddress("00:1B:44:11:3A:B7:00"));
    assertFalse(isMACAddress("00:1B:44:11:3A:B"));
    assertThrows(IllegalArgumentException.class, () -> isMACAddress(null));
    assertThrows(IllegalArgumentException.class, () -> isMACAddress(""));
  }

  @Test
  void testIsMD5() {
    assertTrue(isMD5("d41d8cd98f00b204e9800998ecf8427e"));
    assertFalse(isMD5("Hello World"));
    assertThrows(IllegalArgumentException.class, () -> isMD5(null));
    assertThrows(IllegalArgumentException.class, () -> isMD5(""));
  }

  @Test
  void testIsPortInt() {
    assertTrue(isPort(80));
    assertFalse(isPort(-1));
    assertFalse(isPort(65536));
  }

  @Test
  void testIsPortString() {
    assertTrue(isPort("80"));
    assertFalse(isPort("-1"));
    assertFalse(isPort("65536"));
    assertThrows(IllegalArgumentException.class, () -> isPort(null));
    assertThrows(IllegalArgumentException.class, () -> isPort(""));
  }

  @Test
  void testIsPostalCode() {
    assertTrue(isPostalCode("12345"));
    assertFalse(isPostalCode("Hello World"));
    assertFalse(isPostalCode("123456"));
    assertThrows(IllegalArgumentException.class, () -> isPostalCode(null));
    assertThrows(IllegalArgumentException.class, () -> isPostalCode(""));
  }

  @Test
  void testIsTimeTrue() {
    assertTrue(isTime("12:34"));
    assertTrue(isTime("12:34:56"));
    assertTrue(isTime("16:30:00"));
    assertTrue(isTime("23:59:59"));
    assertTrue(isTime("00:00:00"));
    assertTrue(isTime("00:00"));
    assertTrue(isTime("23:59"));
    assertTrue(isTime("8:30 PM"));
    assertTrue(isTime("8:30 AM"));
    assertTrue(isTime("8:30:00 PM"));
    assertTrue(isTime("8:30:00 AM"));
  }

  @Test
  void testIsTimeFalse() {
    assertFalse(isTime("Hello World"));
    assertFalse(isTime("12:34:56:789"));
    assertFalse(isTime("24:00:00"));
    assertFalse(isTime("00:60:00"));
    assertFalse(isTime("00:00:60"));
    assertFalse(isTime("8:30 PMM"));
    assertFalse(isTime("8:30 AMM"));
    assertFalse(isTime("8:30:00 PMM"));
    assertFalse(isTime("8:30:00 AMM"));
    assertFalse(isTime("8:30:00 PM PM"));
    assertFalse(isTime("8:30:00 AM AM"));
    assertFalse(isTime("8:30:00 PM AM"));
    assertFalse(isTime("8:30:00 AM PM"));
    assertFalse(isTime("8:30:00 PM 8:30:00 AM"));
    assertFalse(isTime("8:30:00 AM 8:30:00 PM"));
    assertFalse(isTime("8:30:00 PM 8:30:00 PM"));
  }

  @Test
  void testIsTimeToThrow() {
    assertThrows(IllegalArgumentException.class, () -> isTime(null));
    assertThrows(IllegalArgumentException.class, () -> isTime(""));
  }
}
