The `FileValidator` class is used to validate files. It has the following methods:

- isValidAudio
    - file (File) - required
        - audioExtensions (String[]) - default: ["mp3", "wav"]
    - exclude (String[]) - (optional) - default: null
        - You can exclude the extensions you don't want to validate
- isValidImage
    - file (File) - required
        - imageExtensions (String[]) - default: ["ico", "jpeg", "png", "gif"]
    - exclude (String[]) - (optional) - default: null
        - You can exclude the extensions you don't want to validate
- isValidPdf
    - file (File) - required
        - pdfExtensions (String) - default: "pdf"
- isValidTxt
    - file (File) - required
        - txtExtensions (String) - default: "txt"

## How to use

### isValidAudio

```java
import static io.github.multiform_validator.FileValidator.isValidAudio;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/audio.mp3");
        System.out.println(isValidAudio(file)); // true | false

        exampleExcludingExtensions();
    }

    public static void exampleExcludingExtensions() {
        File file = new File("src/main/resources/audio.mp3");
        String[] audioExtensions = {"mp3"};
        System.out.println(isValidAudio(file, audioExtensions)); // false
    }
}
```

### isValidImage

```java
import static io.github.multiform_validator.FileValidator.isValidImage;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/image.png");
        System.out.println(isValidImage(file)); // true | false

        exampleExcludingExtensions();
        example2ExcludingExtensions();
    }

    public static void exampleExcludingExtensions() {
        File file = new File("src/main/resources/image.png");
        String[] imageExtensions = {"ico", "jpeg", "png"};
        System.out.println(isValidImage(file, imageExtensions)); // false
    }

    public static void example2ExcludingExtensions() {
        File file = new File("src/main/resources/image.png");
        String[] imageExtensions = {"ico", "jpeg"};
        System.out.println(isValidImage(file, imageExtensions)); // true | false
    }
}
```

### isValidPdf

```java
import static io.github.multiform_validator.FileValidator.isValidPdf;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/file.pdf");
        System.out.println(isValidPdf(file)); // true | false
    }
}
```

### isValidTxt

```java
import static io.github.multiform_validator.FileValidator.isValidTxt;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/file.txt");
        System.out.println(isValidTxt(file)); // true | false
    }
}
```

## Other classes validation methods

- [CnpjValidator](https://multiform-validator.github.io/java/classes/CnpjValidator)
- [CpfValidator](https://multiform-validator.github.io/java/classes/CpfValidator)
- [CreditCardValidator](https://multiform-validator.github.io/java/classes/CreditCardValidator)
- [Utils](https://multiform-validator.github.io/java/classes/Utils)
- [Validate](https://multiform-validator.github.io/java/classes/Validate)
- [Validator](https://multiform-validator.github.io/java/classes/Validator)