import io.github.multiform_validator.Utils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    // ##############################################################################################################
    // ##############################################################################################################
    // ##############################################################################################################
    // Test GetOnlyEmail
    @Test
    void testGetOnlyEmail() {
        // Test with no email found
        assertEquals("No email found", Utils.getOnlyEmail("This is a sample text", null));

        // Test with single email
        assertEquals("test@example.com", Utils.getOnlyEmail("This is a sample text with email test@example.com", null));

        // Test with multiple emails
        Utils.GetOnlyEmailOptionsParams options1 = new Utils.GetOnlyEmailOptionsParams();
        options1.multiple = true;
        assertEquals(
                new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com")),
                Utils.getOnlyEmail("This is a sample text with emails test1@example.com and test2@example.com", options1)
        );

        // Test with multiple emails and clean domain
        Utils.GetOnlyEmailOptionsParams options2 = new Utils.GetOnlyEmailOptionsParams();
        options2.multiple = true;
        options2.cleanDomain = true;
        assertEquals(
                new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com")),
                Utils.getOnlyEmail("This is a sample text with emails test1@example.comAWODI test2@example.comAWDOI awwdawd", options2)
        );

        // Test with multiple emails and clean domain and repeat email
        Utils.GetOnlyEmailOptionsParams options3 = new Utils.GetOnlyEmailOptionsParams();
        options3.multiple = true;
        options3.cleanDomain = true;
        options3.repeatEmail = true;
        assertEquals(
                new ArrayList<>(Arrays.asList("test1@example.com", "test1@example.com", "test2@example.com")),
                Utils.getOnlyEmail("This is a sample text with emails test1@example.comASD test1@example.comASD blabla test2@example.com", options3)
        );
    }
}