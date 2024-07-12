import io.github.multiform_validator.Validate;
import io.github.multiform_validator.Validate.ValidateEmailOptionsParams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {
    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // Test ValidateEmail
    @Test
    void testValidateEmail() {
        // Test with valid email
        assertTrue(Validate.validateEmail("foo@gmail.com"));

        // Test with invalid email
        assertFalse(Validate.validateEmail("foo@bar"));

        // Test with maxLength option
        ValidateEmailOptionsParams options1 = new ValidateEmailOptionsParams();
        options1.maxLength = 10;
        assertFalse(Validate.validateEmail("foo@gmail.com", options1));

        // Test with country option
        ValidateEmailOptionsParams options2 = new ValidateEmailOptionsParams();
        options2.maxLength = 400;
        options2.country = "br";
        assertTrue(Validate.validateEmail("foo@gmail.com.br", options2));
        assertFalse(Validate.validateEmail("foo@gmail.org", options2));

        // Test with validDomains option
        ValidateEmailOptionsParams options3 = new ValidateEmailOptionsParams();
        options3.validDomains = true;
        assertTrue(Validate.validateEmail("foo@gmail.com", options3));
        assertFalse(Validate.validateEmail("foo@bar.com", options3));

        // Test with validDomainsList option
        ValidateEmailOptionsParams options4 = new ValidateEmailOptionsParams();
        options4.validDomainsList.add("@voicemail.com");
        assertTrue(Validate.validateEmail("foo@voicemail.com", options4));
        assertFalse(Validate.validateEmail("foo@gmail.com", options4));

        // Test with validDomains and validDomainsList options at the same time
        ValidateEmailOptionsParams options5 = new ValidateEmailOptionsParams();
        options5.validDomains = true;
        options5.validDomainsList.add("@voicemail.com");
        assertThrows(IllegalArgumentException.class, () -> Validate.validateEmail("foo@gmail.com", options5));

        // Test with invalid domain
        ValidateEmailOptionsParams options6 = new ValidateEmailOptionsParams();
        options6.validDomains = true;
        assertFalse(Validate.validateEmail("foo@bar.com", options6));
    }

    @Test
    void testValidateEmailThrows() {
        // Test with empty email
        assertThrows(IllegalArgumentException.class, () -> Validate.validateEmail(""));

        // Test with null email
        assertThrows(IllegalArgumentException.class, () -> Validate.validateEmail(null));
    }
}