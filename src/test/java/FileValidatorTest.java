import io.github.multiform_validator.FileValidator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileValidatorTest {

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // Image file validation
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
        assertTrue(FileValidator.isValidImage(file, exclude));
    }

    @Test
    void testValidateImageWithValidImageAndValidExclusion() {
        File file = new File(validImagePngPath);
        List<String> exclude = Arrays.asList("gif", "ico", "png");
        assertFalse(FileValidator.isValidImage(file, exclude));
    }

    @Test
    void testValidateImageWithValidImageAndInvalidExclusion() {
        File file = new File(validImagePngPath);
        List<String> exclude = Arrays.asList("gif", "ico");
        assertTrue(FileValidator.isValidImage(file, exclude));
    }

    @Test
    void testValidateImageWithValidPngImage() {
        File file = new File(validImagePngPath);
        assertTrue(FileValidator.isValidImage(file));
    }

    @Test
    void testValidateImageWithInvalidPngImage() {
        File file = new File(invalidImagePngPath);
        assertFalse(FileValidator.isValidImage(file));
    }

    @Test
    void testValidateImageWithValidJpgImage() {
        File file = new File(validImageJpgPath);
        assertTrue(FileValidator.isValidImage(file));
    }

    @Test
    void testValidateWithInvalidJpgImage() {
        File file = new File(invalidImageJpgPath);
        assertFalse(FileValidator.isValidImage(file));
    }

    @Test
    void testValidateImageWithValidGifImage() {
        File file = new File(validImageGifPath);
        assertTrue(FileValidator.isValidImage(file));
    }

    @Test
    void testValidateImageWithInvalidGifImage() {
        File file = new File(invalidImageGifPath);
        assertFalse(FileValidator.isValidImage(file));
    }

    @Test
    void testValidateImageWithValidIcoImage() {
        File file = new File(validImageIcoPath);
        assertTrue(FileValidator.isValidImage(file));
    }

    @Test
    void testValidateImageWithInvalidIcoImage() {
        File file = new File(invalidImageIcoPath);
        assertFalse(FileValidator.isValidImage(file));
    }

    @Test
    void testValidateImageWithNullFile() {
        assertThrows(IllegalArgumentException.class, () -> FileValidator.isValidImage(null));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // Audio file validation

    final String audioBasePath = "src/test/java/assets/isValidAudio";
    final String validAudioMp3Path = audioBasePath + "/valid/valid.mp3";
    final String validAudioWavPath = audioBasePath + "/valid/valid.wav";

    final String invalidAudioMp3Path = audioBasePath + "/invalid/invalid.mp3";
    final String invalidAudioWavPath = audioBasePath + "/invalid/invalid.wav";

    @Test
    void testValidateAudioWithValidMp3Audio() {
        File file = new File(validAudioMp3Path);
        assertTrue(FileValidator.isValidAudio(file));
    }

    @Test
    void testValidateAudioWithInvalidMp3Audio() {
        File file = new File(invalidAudioMp3Path);
        assertFalse(FileValidator.isValidAudio(file));
    }

    @Test
    void testValidateAudioWithValidWavAudio() {
        File file = new File(validAudioWavPath);
        assertTrue(FileValidator.isValidAudio(file));
    }

    @Test
    void testValidateAudioWithInvalidWavAudio() {
        File file = new File(invalidAudioWavPath);
        assertFalse(FileValidator.isValidAudio(file));
    }

    @Test
    void testValidateAudioWithExclusionExceptAudioToBeValidate() {
        File file = new File(validAudioWavPath);
        List<String> exclude = Collections.singletonList("mp3");
        assertTrue(FileValidator.isValidAudio(file, exclude));
    }

    @Test
    void testValidateAudioWithValidAudioAndValidExclusion() {
        File file = new File(validAudioMp3Path);
        List<String> exclude = Collections.singletonList("mp3");
        assertFalse(FileValidator.isValidAudio(file, exclude));
    }

    @Test
    void testValidateAudioWithValidAudioAndInvalidExclusion() {
        File file = new File(validAudioMp3Path);
        List<String> exclude = Collections.singletonList("wav");
        assertTrue(FileValidator.isValidAudio(file, exclude));
    }

    @Test
    void testValidateExcludingAllAudios() {
        File file = new File(validAudioMp3Path);
        List<String> exclude = Arrays.asList("wav", "mp3");
        assertFalse(FileValidator.isValidAudio(file, exclude));
    }

    @Test
    void testValidateAudioWithNullFile() {
        assertThrows(IllegalArgumentException.class, () -> FileValidator.isValidAudio(null));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // Pdf file validation

    final String pdfBasePath = "src/test/java/assets/isValidPdf/";

    final String validPdfPath = pdfBasePath + "valid.pdf";
    final String invalidPdfPath = pdfBasePath + "invalid.pdf";

    @Test
    void testValidatePdf() {
        File file = new File(validPdfPath);
        assertTrue(FileValidator.isValidPdf(file));
    }

    @Test
    void testValidatePdfWithInvalidPdf() {
        File file = new File(invalidPdfPath);
        assertFalse(FileValidator.isValidPdf(file));
    }

    @Test
    void testValidatePdfWithNullFile() {
        assertThrows(IllegalArgumentException.class, () -> FileValidator.isValidPdf(null));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
    // Txt file validation

    final String txtBasePath = "src/test/java/assets/isValidTxt";

    final String validTxtPath = txtBasePath + "/valid.txt";
    final String invalidTxtPath = txtBasePath + "/invalid.txt";

    @Test
    void testValidateTxt() {
        File file = new File(validTxtPath);
        assertTrue(FileValidator.isValidTxt(file));
    }

    @Test
    void testValidateTxtWithInvalidTxt() {
        File file = new File(invalidTxtPath);
        assertFalse(FileValidator.isValidTxt(file));
    }

    @Test
    void testValidateTxtWithNullFile() {
        assertThrows(IllegalArgumentException.class, () -> FileValidator.isValidTxt(null));
    }

    // ############################################################################################################
    // ############################################################################################################
    // ############################################################################################################
}