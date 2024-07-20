import static io.github.multiform_validator.file.ImageValidator.isValidImage;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ImageValidatorTest {
  final String imageBasePath = "src/test/java/assets/isValidImage";
  final String validImagePngPath = imageBasePath + "/valid/valid.png";
  final String validImageJpgPath = imageBasePath + "/valid/valid.jpg";
  final String validImageGifPath = imageBasePath + "/valid/valid.gif";
  final String validImageIcoPath = imageBasePath + "/valid/valid.ico";

  final String invalidImagePngPath = imageBasePath + "/invalid/invalid.png";
  final String invalidImageJpgPath = imageBasePath + "/invalid/invalid.jpg";
  final String invalidImageGifPath = imageBasePath + "/invalid/invalid.gif";
  final String invalidImageIcoPath = imageBasePath + "/invalid/invalid.ico";

  @Test
  void testValidateImageWithExclusionExceptImageToBeValidate() {
    File file = new File(validImageJpgPath);
    List<String> exclude = Arrays.asList("gif", "ico", "png");
    assertTrue(isValidImage(file, exclude));
  }

  @Test
  void testValidateImageWithValidImageAndValidExclusion() {
    File file = new File(validImagePngPath);
    List<String> exclude = Arrays.asList("gif", "ico", "png");
    assertFalse(isValidImage(file, exclude));
  }

  @Test
  void testValidateImageWithValidImageAndInvalidExclusion() {
    File file = new File(validImagePngPath);
    List<String> exclude = Arrays.asList("gif", "ico");
    assertTrue(isValidImage(file, exclude));
  }

  @Test
  void testValidateImageWithValidPngImage() {
    File file = new File(validImagePngPath);
    assertTrue(isValidImage(file));
  }

  @Test
  void testValidateImageWithInvalidPngImage() {
    File file = new File(invalidImagePngPath);
    assertFalse(isValidImage(file));
  }

  @Test
  void testValidateImageWithValidJpgImage() {
    File file = new File(validImageJpgPath);
    assertTrue(isValidImage(file));
  }

  @Test
  void testValidateWithInvalidJpgImage() {
    File file = new File(invalidImageJpgPath);
    assertFalse(isValidImage(file));
  }

  @Test
  void testValidateImageWithValidGifImage() {
    File file = new File(validImageGifPath);
    assertTrue(isValidImage(file));
  }

  @Test
  void testValidateImageWithInvalidGifImage() {
    File file = new File(invalidImageGifPath);
    assertFalse(isValidImage(file));
  }

  @Test
  void testValidateImageWithValidIcoImage() {
    File file = new File(validImageIcoPath);
    assertTrue(isValidImage(file));
  }

  @Test
  void testValidateImageWithInvalidIcoImage() {
    File file = new File(invalidImageIcoPath);
    assertFalse(isValidImage(file));
  }

  @Test
  void testValidateImageWithNullFile() {
    assertThrows(IllegalArgumentException.class, () -> isValidImage(null));
  }
}
