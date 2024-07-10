import org.junit.jupiter.api.Test;

import static io.github.multiform_validator.EmailValidator.isEmail;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    @Test
    void testValidEmail() {
        assertTrue(isEmail("foo@bar.com"));
        assertTrue(isEmail("Foo@Bar.com"));
        assertTrue(isEmail("foo@bar.com.br"));
    }

    @Test
    void testInvalidEmail() {
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
}