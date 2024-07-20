import static org.junit.jupiter.api.Assertions.*;

import io.github.multiform_validator.format.EmailValidator;
import io.github.multiform_validator.format.email.ValidateEmailBuilder;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class EmailValidatorTest {
  @Test
  void testValidateEmail() {
    // Test with valid email
    assertTrue(EmailValidator.validateEmail("foo@gmail.com"));

    // Test with invalid email
    assertFalse(EmailValidator.validateEmail("foo@bar"));

    // Test with maxLength option
    ValidateEmailBuilder options1 = new ValidateEmailBuilder();
    options1.setMaxLength(10);
    assertFalse(EmailValidator.validateEmail("foo@gmail.com", options1));

    // Test with country option
    ValidateEmailBuilder options2 = new ValidateEmailBuilder();
    options2.setMaxLength(400);
    options2.setCountry("br");
    assertTrue(EmailValidator.validateEmail("foo@gmail.com.br", options2));
    assertFalse(EmailValidator.validateEmail("foo@gmail.org", options2));

    // Test with validDomains option
    ValidateEmailBuilder options3 = new ValidateEmailBuilder();
    options3.setValidDomains(true);
    assertTrue(EmailValidator.validateEmail("foo@gmail.com", options3));
    assertFalse(EmailValidator.validateEmail("foo@bar.com", options3));

    // Test with validDomainsList option
    ValidateEmailBuilder options4 = new ValidateEmailBuilder();
    options4.setValidDomainsList(Collections.singletonList("@voicemail.com"));
    assertTrue(EmailValidator.validateEmail("foo@voicemail.com", options4));
    assertFalse(EmailValidator.validateEmail("foo@gmail.com", options4));

    // Test with validDomains and validDomainsList options at the same time
    ValidateEmailBuilder options5 = new ValidateEmailBuilder();
    options5.setValidDomains(true);
    options5.setValidDomainsList(Collections.singletonList("@voicemail.com"));
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailValidator.validateEmail("foo@gmail.com", options5));

    // Test with invalid domain
    ValidateEmailBuilder options6 = new ValidateEmailBuilder();
    options6.setValidDomains(true);
    assertFalse(EmailValidator.validateEmail("foo@bar.com", options6));
  }

  @Test
  void testValidateEmailThrows() {
    // Test with empty email
    assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail(""));

    // Test with null email
    assertThrows(IllegalArgumentException.class, () -> EmailValidator.validateEmail(null));
  }
}
