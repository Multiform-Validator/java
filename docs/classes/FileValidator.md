# FileValidator

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

```java
import io.github.multiform_validator.FileValidator;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("path/to/file");
        System.out.println(FileValidator.isValidAudio(file)); // true | false
        System.out.println(FileValidator.isValidImage(file)); // true | false
        System.out.println(FileValidator.isValidPdf(file)); // true | false
        System.out.println(FileValidator.isValidTxt(file)); // true | false

        exampleExcludingExtensions();
    }

    public static void exampleExcludingExtensions() {
        File file = new File("path/to/file");
        String[] audioExtensions = {"mp3"};
        String[] imageExtensions = {"ico", "jpeg", "png"};
        System.out.println(FileValidator.isValidAudio(file, audioExtensions)); // true | false
        System.out.println(FileValidator.isValidImage(file, imageExtensions)); // false | true
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

## Return to [Home](https://multiform-validator.github.io/java/)