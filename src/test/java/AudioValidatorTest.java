import static io.github.multiform_validator.file.AudioValidator.isValidAudio;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class AudioValidatorTest {
  final String audioBasePath = "src/test/java/assets/isValidAudio";
  final String validAudioMp3Path = audioBasePath + "/valid/valid.mp3";
  final String validAudioWavPath = audioBasePath + "/valid/valid.wav";

  final String invalidAudioMp3Path = audioBasePath + "/invalid/invalid.mp3";
  final String invalidAudioWavPath = audioBasePath + "/invalid/invalid.wav";

  @Test
  void testValidateAudioWithValidMp3Audio() {
    File file = new File(validAudioMp3Path);
    assertTrue(isValidAudio(file));
  }

  @Test
  void testValidateAudioWithInvalidMp3Audio() {
    File file = new File(invalidAudioMp3Path);
    assertFalse(isValidAudio(file));
  }

  @Test
  void testValidateAudioWithValidWavAudio() {
    File file = new File(validAudioWavPath);
    assertTrue(isValidAudio(file));
  }

  @Test
  void testValidateAudioWithInvalidWavAudio() {
    File file = new File(invalidAudioWavPath);
    assertFalse(isValidAudio(file));
  }

  @Test
  void testValidateAudioWithExclusionExceptAudioToBeValidate() {
    File file = new File(validAudioWavPath);
    List<String> exclude = Collections.singletonList("mp3");
    assertTrue(isValidAudio(file, exclude));
  }

  @Test
  void testValidateAudioWithValidAudioAndValidExclusion() {
    File file = new File(validAudioMp3Path);
    List<String> exclude = Collections.singletonList("mp3");
    assertFalse(isValidAudio(file, exclude));
  }

  @Test
  void testValidateAudioWithValidAudioAndInvalidExclusion() {
    File file = new File(validAudioMp3Path);
    List<String> exclude = Collections.singletonList("wav");
    assertTrue(isValidAudio(file, exclude));
  }

  @Test
  void testValidateExcludingAllAudios() {
    File file = new File(validAudioMp3Path);
    List<String> exclude = Arrays.asList("wav", "mp3");
    assertFalse(isValidAudio(file, exclude));
  }

  @Test
  void testValidateAudioWithNullFile() {
    assertThrows(IllegalArgumentException.class, () -> isValidAudio(null));
  }
}
