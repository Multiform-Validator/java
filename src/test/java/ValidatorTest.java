import io.github.multiform_validator.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isAscii
    @Test
    void testIsAscii() {
        assertTrue(Validator.isAscii("Hello World"));
        assertTrue(Validator.isAscii("0123456789"));
        assertTrue(Validator.isAscii("abcdefghijklmnopqrstuvwxyz"));
        assertTrue(Validator.isAscii("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        assertTrue(Validator.isAscii("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"));
        assertFalse(Validator.isAscii("こんにちは"));
        assertFalse(Validator.isAscii("你好"));
        assertFalse(Validator.isAscii("안녕하세요"));
        assertFalse(Validator.isAscii("مرحبا"));
        assertFalse(Validator.isAscii("नमस्ते"));
        assertFalse(Validator.isAscii("สวัสดี"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isAscii(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isAscii(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isBase64

    @Test
    void testIsBase64() {
        assertTrue(Validator.isBase64("SGVsbG8gV29ybGQ="));
        assertTrue(Validator.isBase64("MTIzNDU2Nzg5"));
        assertTrue(Validator.isBase64("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo="));
        assertTrue(Validator.isBase64("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVo="));
        assertFalse(Validator.isBase64("Hello World"));
        assertFalse(Validator.isBase64("こんにちは"));
        assertFalse(Validator.isBase64("你好"));
        assertFalse(Validator.isBase64("안녕하세요"));
        assertFalse(Validator.isBase64("مرحبا"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isBase64(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isBase64(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isCEP

    @Test
    void testIsCEP() {
        assertTrue(Validator.isCEP("12345678"));
        assertFalse(Validator.isCEP("1234567890"));
        assertFalse(Validator.isCEP("abcdefgh"));
        assertFalse(Validator.isCEP("1234567"));
        assertTrue(Validator.isCEP("12345-678"));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isDate

    @Test
    void testIsDateTrue() {
        // Valid date formats
        assertTrue(Validator.isDate("2022-01-01")); // ISO 8601 (YYYY-MM-DD)
        assertTrue(Validator.isDate("01/01/2022")); // Common US format (MM/DD/YYYY)
        assertTrue(Validator.isDate("01-01-2022")); // Common EU format (DD-MM-YYYY)
        assertTrue(Validator.isDate("2022/01/01")); // ISO alternate (YYYY/MM/DD)
        assertTrue(Validator.isDate("01.01.2022")); // Format with dots (DD.MM.YYYY)
        assertTrue(Validator.isDate("2022.01.01")); // Format with dots (YYYY.MM.DD)
        assertTrue(Validator.isDate("01-Jan-2022")); // Format with abbreviated month (DD-MMM-YYYY)
        assertTrue(Validator.isDate("01-January-2022")); // Format with full month name (DD-Month-YYYY)
        assertTrue(Validator.isDate("01-Jan-22")); // Format with abbreviated month and two-digit year (DD-MMM-YY)
        assertTrue(Validator.isDate("01-January-22")); // Format with full month name and two-digit year (DD-Month-YY)
    }

    @Test
    void testIsDateWithNumbersTrue() {
        // Valid date-time formats
        assertTrue(Validator.isDate("2022-01-01T10:15:30")); // ISO 8601 (YYYY-MM-DDTHH:MM:SS)
        assertTrue(Validator.isDate("2022-01-01 10:15:30")); // Space-separated date and time
        assertTrue(Validator.isDate("2022/01/01 10:15:30")); // Slash-separated date and time
        assertTrue(Validator.isDate("01-01-2022 10:15:30")); // Hyphen-separated date and time (DD-MM-YYYY)
        assertTrue(Validator.isDate("01.01.2022 10:15:30")); // Dot-separated date and time (DD.MM.YYYY)
        assertTrue(Validator.isDate("2022.01.01 10:15:30")); // Dot-separated date and time (YYYY.MM.DD)
        assertTrue(Validator.isDate("01-Jan-2022 10:15:30")); // Abbreviated month date and time (DD-MMM-YYYY)
        assertTrue(Validator.isDate("01-January-2022 10:15:30")); // Full month name date and time (DD-Month-YYYY)
        assertTrue(Validator.isDate("01-Jan-22 10:15:30")); // Abbreviated month and two-digit year date and time (DD-MMM-YY)
        assertTrue(Validator.isDate("01-January-22 10:15:30")); // Full month name and two-digit year date and time (DD-Month-YY)
    }

    @Test
    void testIsDateWithNumbersFalse() {
        // Invalid date-time formats
        assertFalse(Validator.isDate("2022-13-01T10:15:30")); // Invalid month
        assertFalse(Validator.isDate("2022-01-32T10:15:30")); // Invalid day
        assertFalse(Validator.isDate("01-01-22T10:15:30"));   // Invalid year format
        assertFalse(Validator.isDate("2022-01-01T25:15:30")); // Invalid hour
        assertFalse(Validator.isDate("2022-01-01T10:60:30")); // Invalid minute
        assertFalse(Validator.isDate("2022-01-01T10:15:60")); // Invalid second
        assertFalse(Validator.isDate("01-01-22 25:15:30"));   // Invalid hour
        assertFalse(Validator.isDate("01-01-22 10:60:30"));   // Invalid minute
        assertFalse(Validator.isDate("01-01-22 10:15:60"));   // Invalid second

        // Null and empty string
        assertThrows(IllegalArgumentException.class, () -> Validator.isDate(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isDate(""));
    }

    @Test
    void testIsDateFalse() {
        // Invalid date formats
        assertFalse(Validator.isDate("2022-13-01")); // Invalid month
        assertFalse(Validator.isDate("2022-01-32")); // Invalid day
        assertFalse(Validator.isDate("01-01-22"));   // Invalid year format
        assertFalse(Validator.isDate("abc-def-ghi")); // Invalid characters
        assertFalse(Validator.isDate("01-01-22 10:15:30")); // Invalid format
        assertFalse(Validator.isDate("01-01-22abc")); // Invalid format

        // Null and empty string
        assertThrows(IllegalArgumentException.class, () -> Validator.isDate(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isDate(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isDecimal

    @Test
    void testIsDecimal() {
        assertTrue(Validator.isDecimal("3.14"));
        assertFalse(Validator.isDecimal("42"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isDecimal(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isDecimal(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isEmail

    @Test
    void testValidEmail() {
        assertTrue(Validator.isEmail("foo@bar.com"));
        assertTrue(Validator.isEmail("Foo@Bar.com"));
        assertTrue(Validator.isEmail("foo@bar.com.br"));
    }

    @Test
    void testInvalidEmail1() {
        assertFalse(Validator.isEmail("foo@bar"));
        assertFalse(Validator.isEmail("1foo@bar.com"));
        assertFalse(Validator.isEmail("foo@1bar.com"));
        assertFalse(Validator.isEmail("foo@bar.1com"));
        assertFalse(Validator.isEmail("foo.bar.com"));
        assertFalse(Validator.isEmail("joaoaoao@gmail.com.com"));
        assertFalse(Validator.isEmail("joao..@gmail.com"));
        assertFalse(Validator.isEmail("joao.@gmail.com"));
        assertFalse(Validator.isEmail("joao@@gmail.com"));
        assertFalse(Validator.isEmail("joao@gmail..com"));
        assertFalse(Validator.isEmail(".foo@bar.com"));
        assertFalse(Validator.isEmail(",foo@bar.com"));
        assertFalse(Validator.isEmail("!foo@bar.com"));
        assertFalse(Validator.isEmail("@foo@bar.com"));
        assertFalse(Validator.isEmail("#foo@bar.com"));
        assertFalse(Validator.isEmail("$foo@bar.com"));
        assertFalse(Validator.isEmail("foo@@bar.com"));
        assertFalse(Validator.isEmail("foo@bar.com.br.br"));
        assertFalse(Validator.isEmail("foo@bar.com.com.br.br"));
        assertFalse(Validator.isEmail("foo@bar.com.com.br"));
    }

    @Test
    void testInvalidEmail2() {
        assertFalse(Validator.isEmail("foo!@bar.com"));
        assertFalse(Validator.isEmail("foo bar@baz.com"));
        assertFalse(Validator.isEmail(" foo@bar.com"));
        assertFalse(Validator.isEmail("foo@bar.com "));
        assertFalse(Validator.isEmail("foo..bar@baz.com"));
        assertFalse(Validator.isEmail("foo@@bar.com"));
        assertFalse(Validator.isEmail("foo@bar..com"));
        assertFalse(Validator.isEmail("foo..bar@baz.com"));
        assertFalse(Validator.isEmail("foo@bar.com.."));
        assertFalse(Validator.isEmail("foo@bar..com"));
        assertFalse(Validator.isEmail("foo..@bar.com"));
        assertFalse(Validator.isEmail("foo@bar..com."));
        assertFalse(Validator.isEmail("foo..@bar..com."));
        assertFalse(Validator.isEmail("foo@bar.com..."));
        assertFalse(Validator.isEmail("foo@bar!com"));
        assertFalse(Validator.isEmail("foo!@bar.com"));
        assertFalse(Validator.isEmail("foo@bar.c"));
        assertFalse(Validator.isEmail("foo@bar."));
        assertFalse(Validator.isEmail("foo@bar"));
        assertFalse(Validator.isEmail("foo@bar."));
        assertFalse(Validator.isEmail("foo@bar.."));
        assertFalse(Validator.isEmail("foo@bar..."));
    }

    @Test
    void testEmptyEmail() {
        assertFalse(Validator.isEmail(""));
    }

    @Test
    void testNullEmail() {
        assertThrows(NullPointerException.class, () -> Validator.isEmail(null));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isMACAddress

    @Test
    void testIsMACAddress() {
        assertTrue(Validator.isMACAddress("00:1B:44:11:3A:B7"));
        assertFalse(Validator.isMACAddress("Hello World"));
        assertFalse(Validator.isMACAddress("00:1B:44:11:3A:B7:00"));
        assertFalse(Validator.isMACAddress("00:1B:44:11:3A:B"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isMACAddress(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isMACAddress(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isMD5

    @Test
    void testIsMD5() {
        assertTrue(Validator.isMD5("d41d8cd98f00b204e9800998ecf8427e"));
        assertFalse(Validator.isMD5("Hello World"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isMD5(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isMD5(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isNumber

    @Test
    void testIsNumber() {
        assertTrue(Validator.isNumber("42"));
        assertFalse(Validator.isNumber("Hello World"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isNumber(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isNumber(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isPort

    @Test
    void testIsPortInt() {
        assertTrue(Validator.isPort(80));
        assertFalse(Validator.isPort(-1));
        assertFalse(Validator.isPort(65536));
    }

    @Test
    void testIsPortString() {
        assertTrue(Validator.isPort("80"));
        assertFalse(Validator.isPort("-1"));
        assertFalse(Validator.isPort("65536"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isPort(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isPort(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isPostalCode

    @Test
    void testIsPostalCode() {
        assertTrue(Validator.isPostalCode("12345"));
        assertFalse(Validator.isPostalCode("Hello World"));
        assertFalse(Validator.isPostalCode("123456"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isPostalCode(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isPostalCode(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // isTime

    @Test
    void testIsTimeTrue() {
        assertTrue(Validator.isTime("12:34"));
        assertTrue(Validator.isTime("12:34:56"));
        assertTrue(Validator.isTime("16:30:00"));
        assertTrue(Validator.isTime("23:59:59"));
        assertTrue(Validator.isTime("00:00:00"));
        assertTrue(Validator.isTime("00:00"));
        assertTrue(Validator.isTime("23:59"));
        assertTrue(Validator.isTime("8:30 PM"));
        assertTrue(Validator.isTime("8:30 AM"));
        assertTrue(Validator.isTime("8:30:00 PM"));
        assertTrue(Validator.isTime("8:30:00 AM"));
    }

    @Test
    void testIsTimeFalse() {
        assertFalse(Validator.isTime("Hello World"));
        assertFalse(Validator.isTime("12:34:56:789"));
        assertFalse(Validator.isTime("24:00:00"));
        assertFalse(Validator.isTime("00:60:00"));
        assertFalse(Validator.isTime("00:00:60"));
        assertFalse(Validator.isTime("8:30 PMM"));
        assertFalse(Validator.isTime("8:30 AMM"));
        assertFalse(Validator.isTime("8:30:00 PMM"));
        assertFalse(Validator.isTime("8:30:00 AMM"));
        assertFalse(Validator.isTime("8:30:00 PM PM"));
        assertFalse(Validator.isTime("8:30:00 AM AM"));
        assertFalse(Validator.isTime("8:30:00 PM AM"));
        assertFalse(Validator.isTime("8:30:00 AM PM"));
        assertFalse(Validator.isTime("8:30:00 PM 8:30:00 AM"));
        assertFalse(Validator.isTime("8:30:00 AM 8:30:00 PM"));
        assertFalse(Validator.isTime("8:30:00 PM 8:30:00 PM"));
    }

    @Test
    void testIsTimeToThrow() {
        assertThrows(IllegalArgumentException.class, () -> Validator.isTime(null));
        assertThrows(IllegalArgumentException.class, () -> Validator.isTime(""));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
}