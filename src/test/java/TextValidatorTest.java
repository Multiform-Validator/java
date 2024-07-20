import static io.github.multiform_validator.file.FileValidator.isValidTxt;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextValidatorTest {
  private String validTxtPath;
  private String invalidTxtPath;

  @BeforeEach
  void setUp() {
    String txtBasePath = "src/test/java/assets/isValidTxt";
    validTxtPath = txtBasePath + "/valid.txt";
    invalidTxtPath = txtBasePath + "/invalid.txt";
  }

  @Test
  void testValidateTxt() {
    File file = new File(validTxtPath);
    assertTrue(isValidTxt(file));
  }

  @Test
  void testValidateTxtWithInvalidTxt() {
    File file = new File(invalidTxtPath);
    assertFalse(isValidTxt(file));
  }

  @Test
  void testValidateTxtWithNullFile() {
    assertThrows(IllegalArgumentException.class, () -> isValidTxt(null));
  }
}
