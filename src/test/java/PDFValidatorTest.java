import static io.github.multiform_validator.file.FileValidator.isValidPdf;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PDFValidatorTest {
  private String validPdfPath;
  private String invalidPdfPath;

  @BeforeEach
  void setUp() {
    String pdfBasePath = "src/test/java/assets/isValidPdf/";
    validPdfPath = pdfBasePath + "valid.pdf";
    invalidPdfPath = pdfBasePath + "invalid.pdf";
  }

  @Test
  void testValidatePdf() {
    File file = new File(validPdfPath);
    assertTrue(isValidPdf(file));
  }

  @Test
  void testValidatePdfWithInvalidPdf() {
    File file = new File(invalidPdfPath);
    assertFalse(isValidPdf(file));
  }

  @Test
  void testValidatePdfWithNullFile() {
    assertThrows(IllegalArgumentException.class, () -> isValidPdf(null));
  }
}
