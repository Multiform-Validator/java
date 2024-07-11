import io.github.multiform_validator.FileValidator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileValidatorTest {
    final String basePath = "src/test/java/assets/isValidImage/";

    @Test
    void testValidateImage() {
        File file = new File(basePath + "/valid/valid.jpg");
        assertTrue(FileValidator.validateImage(file));
    }

    @Test
    void testValidateImageWithExclusion() {
        File file = new File(basePath + "/valid/valid.jpg");
        List<String> exclude = Arrays.asList("gif", "ico", "png");
        assertTrue(FileValidator.validateImage(file, exclude));
    }

    @Test
    void testValidateImageWithInvalidImage() {
        File file = new File(basePath + "/invalid/invalid.png");
        assertFalse(FileValidator.validateImage(file));
    }

    @Test
    void testValidateImageWithValidImageAndValidExclusion() {
        File file = new File(basePath + "/valid/valid.png");
        List<String> exclude = Arrays.asList("gif", "ico", "png");
        assertFalse(FileValidator.validateImage(file, exclude));
    }

    @Test
    void testValidateImageWithValidImageAndInvalidExclusion() {
        File file = new File(basePath + "/valid/valid.png");
        List<String> exclude = Arrays.asList("gif", "ico");
        assertTrue(FileValidator.validateImage(file, exclude));
    }

    @Test
    void testValidateImageWithNullFile() {
        assertThrows(IllegalArgumentException.class, () -> FileValidator.validateImage(null));
    }
}