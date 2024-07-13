> [!NOTE]
> I accept help to make the version of the other programming languages.

# Multiform-validator

## JAVA >=8

## How to install

follow the steps below to use the library in your project.

https://jitpack.io/#multiform-validator/java/

## Available methods - JAVA (0.0.4)v

- CnpjValidator
    - cnpjIsValid

- CpfValidator
    - cpfIsValid

- CreditCardValidator
    - isCreditCardValid
    - identifyCreditCard

- FileValidator
    - isValidAudio
        - file (File) - required
            - audioExtensions (String[]) - default: ["mp3", "wav"]
            - You can exclude the extensions you don't want to validate
    - isValidImage
        - file (File) - required
            - imageExtensions (String[]) - default: ["ico", "jpeg", "png", "gif"]
            - You can exclude the extensions you don't want to validate
    - isValidPdf
        - file (File) - required
            - pdfExtensions (String) - default: "pdf"
    - isValidTxt
        - file (File) - required
            - txtExtensions (String) - default: "txt"
- Utils
    - getOnlyEmail
        - getOnlyEmailWithOptions (options)
            - multiple (boolean) - default: false
            - cleanDomain (boolean | Arrays<String>) - default: false
            - repeatEmail (boolean) - default: false

- Validate
    - validateEmail
        - maxLength (int) - default: 400
        - country (String) - default: ""
        - validDomains (boolean) - default: false
        - validDomainsList (String[]) - default: ["@gmail.com",
          "@outlook.com",
          "@yahoo.com",
          "@icloud.com",
          "@hotmail.com",
          "@mail.ru",
          "@yandex.ru",
          "@gmx.com",
          "@zoho.com",
          "@protonmail.com",
          "@protonmail.ch"]

- Validator
    - isAscii
    - isCEP
    - isDate
    - isDecimal
    - isEmail
    - isMACAddress
    - isNumber
    - isPort
    - isPostalCode
    - isTime

## How to use

### CnpjValidator

```java
// You can import the class or use the full path

import io.github.multiform_validator.CnpjValidator;

public class Main {
    public static void main(String[] args) {
        String cnpjTrue = "69.807.668/0001-41";
        String cnpjFalse = "61.807.661/0001-48";
        System.out.println(CnpjValidator.cnpjIsValid(cnpjTrue)); // true
        System.out.println(CnpjValidator.cnpjIsValid(cnpjFalse)); // false
    }
}
```

### CpfValidator

```java
// You can also import the method as static or use the full path

import static io.github.multiform_validator.CpfValidator.cpfIsValid;

public class Main {
    public static void main(String[] args) {
        String cpfTrue = "123.456.789-09";
        String cpfFalse = "123.456.789-10";
        System.out.println(cpfIsValid(cpfTrue)); // true
        System.out.println(cpfIsValid(cpfFalse)); // false
    }
}
```

### CreditCardValidator

```java
import io.github.multiform_validator.CreditCardValidator;

public class Main {
    public static void main(String[] args) {
        String creditCard = "4532 8770 0040 4166";
        System.out.println(CreditCardValidator.isCreditCardValid(creditCard)); // true
        System.out.println(CreditCardValidator.identifyCreditCard(creditCard)); // Visa
    }
}
```

### FileValidator

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

### Utils

```java
import io.github.multiform_validator.Utils;

public class Main {
    public static void main(String[] args) {
        String msg1 = "This is a message example with foo@bar.com email to test";
        System.out.println(Utils.getOnlyEmail(msg1, null)); // foo@bar.com

        String msg2 = "Example two foo1@bar.com and foo2@bar.com";
        // With options
        Utils.GetOnlyEmailOptionsParams options = new Utils.GetOnlyEmailOptionsParams();
        options.setMultiple(true);
        System.out.println(Utils.getOnlyEmailWithOptions(msg2, options)); // [foo1@bar.com, foo2@bar.com]
    }
}
```

### Validate

```java
import io.github.multiform_validator.Validate;
import io.github.multiform_validator.Validate.ValidateEmailOptionsParams;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        validateEmailExample();
    }

    public static void validateEmailExample() {
        // IMPORTANT: validDomains can not be used with validDomainsList, you can use only one of them

        // Basic email validation
        boolean isValid = Validate.validateEmail("example@example.com");
        System.out.println("Is valid: " + isValid); // Expected: true

        // Email validation with options: maxLength
        ValidateEmailOptionsParams optionsMaxLength = new ValidateEmailOptionsParams();
        optionsMaxLength.setMaxLength(10); // Setting max length to 10, which should fail for longer emails
        boolean isValidMaxLength = Validate.validateEmail("longemail@example.com", optionsMaxLength);
        System.out.println("Is valid with maxLength: " + isValidMaxLength); // Expected: false

        // Email validation with options: country specific
        ValidateEmailOptionsParams optionsCountry = new ValidateEmailOptionsParams();
        optionsCountry.setCountry("us"); // Expecting an email from the US
        boolean isNotValidCountry = Validate.validateEmail("example@domain.com", optionsCountry);
        boolean isValidCountry = Validate.validateEmail("example@domain.com.us", optionsCountry);
        System.out.println("Is not valid with country: " + isNotValidCountry); // Expected: false
        System.out.println("Is valid with country: " + isValidCountry); // Expected: true

        // Email validation with options: validDomains
        ValidateEmailOptionsParams optionsValidDomains = new ValidateEmailOptionsParams();
        optionsValidDomains.setValidDomains(true); // Only allow certain domains (implementation specific)
        boolean isValidValidDomains = Validate.validateEmail("example@gmail.com", optionsValidDomains);
        System.out.println("Is valid with validDomains: " + isValidValidDomains); // Expected: true

        // Email validation with options: validDomainsList
        ValidateEmailOptionsParams optionsValidDomainsList = new ValidateEmailOptionsParams();
        optionsValidDomainsList.setValidDomainsList(Collections.singletonList("specificdomain.com")); // Adding a specific domain to the list
        boolean isValidValidDomainsList = Validate.validateEmail("example@specificdomain.com", optionsValidDomainsList);
        System.out.println("Is valid with validDomainsList: " + isValidValidDomainsList); // Expected: true
    }
}
```

### Validator

```java
import io.github.multiform_validator.Validator;

public class Main {
    public static void main(String[] args) {
        validMethods();
        invalidMethods();
    }

    public void validMethods() {
        System.out.println(Validator.isAscii("foo")); // true
        System.out.println(Validator.isCEP("12345-678")); // true
        System.out.println(Validator.isDate("2021-01-01")); // true
        System.out.println(Validator.isDecimal("1.5")); // true
        System.out.println(Validator.isMACAddress("00:00:00:00:00:00")); // true
        System.out.println(Validator.isNumber("123")); // true
        System.out.println(Validator.isPort("8080")); // true
        System.out.println(Validator.isPostalCode("12345-678")); // true
        System.out.println(Validator.isTime("12:00")); // true
        System.out.println(Validator.isEmail("foo@bar.com")); // true
    }

    public void invalidMethods() {
        System.out.println(Validator.isAscii("こんにちは")); // false
        System.out.println(Validator.isCEP("12345678")); // false
        System.out.println(Validator.isDate("2021-01-32")); // false
        System.out.println(Validator.isDecimal("1.5.5")); // false
        System.out.println(Validator.isMACAddress("00:00:00:00:00:00:00")); // false
        System.out.println(Validator.isNumber("123a")); // false
        System.out.println(Validator.isPort("8080a")); // false
        System.out.println(Validator.isPostalCode("12345678")); // false
        System.out.println(Validator.isTime("12:00:00")); // false
        System.out.println(Validator.isEmail("foo@bar")); // false
    }
}
```

Lib is in development, there's other validators that you can use, but they are not yet documented.
