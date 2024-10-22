import static io.github.multiform_validator.format.EmailValidator.getOnlyEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.multiform_validator.format.email.OnlyEmailParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class UtilsTest {

  @Test
  void testGetOnlyEmail() {
    // Test with no email found
    assertEquals("No email found", getOnlyEmail("This is a sample text", null));

    // Test with single email
    assertEquals(
        "test@example.com",
        getOnlyEmail("This is a sample text with email test@example.com", null));

    // Test with multiple emails
    OnlyEmailParams options1 = new OnlyEmailParams();
    options1.setMultiple(true);
    assertEquals(
        new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com")),
        getOnlyEmail(
            "This is a sample text with emails test1@example.com and test2@example.com", options1));

    // Test with multiple emails and clean domain
    OnlyEmailParams options2 = new OnlyEmailParams();
    options2.setMultiple(true);
    options2.setCleanDomain(true);
    assertEquals(
        new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com")),
        getOnlyEmail(
            "This is a sample text with emails test1@example.comAWODI test2@example.comAWDOI awwdawd",
            options2));

    // Test with multiple emails and clean domain and repeat email
    OnlyEmailParams options3 = new OnlyEmailParams();
    options3.setMultiple(true);
    options3.setCleanDomain(true);
    options3.setRepeatEmail(true);
    assertEquals(
        new ArrayList<>(
            Arrays.asList("test1@example.com", "test1@example.com", "test2@example.com")),
        getOnlyEmail(
            "This is a sample text with emails test1@example.comASD test1@example.comASD blabla test2@example.com",
            options3));

    // Test with multiple emails and repeated email however repeatEmail is false
    OnlyEmailParams options4 = new OnlyEmailParams();
    options4.setMultiple(true);
    options4.setCleanDomain(true);
    options4.setRepeatEmail(false);
    assertEquals(
        new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com")),
        getOnlyEmail(
            "vails test1@example.comASD test1@example.comASD blabla test2@example.com", options4));

    // Test with clean domain as false
    OnlyEmailParams options5 = new OnlyEmailParams();
    options5.setMultiple(true);
    options5.setCleanDomain(false);
    assertEquals(
        new ArrayList<>(Arrays.asList("test1@example.comAAA", "test2@example.com.br")),
        getOnlyEmail("vails test1@example.comAAA , test2@example.com.br yes no", options5));

    // Test passing own clean domain
    OnlyEmailParams options6 = new OnlyEmailParams();
    options6.setMultiple(true);
    options6.setCleanDomain(Collections.singletonList(".own"));
    assertEquals(
        new ArrayList<>(Arrays.asList("test1@com.own", "test2@com.own")),
        getOnlyEmail("vails test1@com.ownASDAW , test2@com.ownyes no", options6));
  }
}
