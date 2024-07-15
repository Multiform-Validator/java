# Validate

The `Validate` class is a utility class with some methods to validate data. It has the following methods:

- validateEmail
    - email (String) - required
    - ValidateEmailOptionsParams (options) - default: null
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

## How to use

### validateEmail

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

## Other classes validation methods

- [CnpjValidator](https://multiform-validator.github.io/java/classes/CnpjValidator)
- [CpfValidator](https://multiform-validator.github.io/java/classes/CpfValidator)
- [CreditCardValidator](https://multiform-validator.github.io/java/classes/CreditCardValidator)
- [FileValidator](https://multiform-validator.github.io/java/classes/FileValidator)
- [Utils](https://multiform-validator.github.io/java/classes/Utils)
- [Validator](https://multiform-validator.github.io/java/classes/Validator)

## Return to [Home](https://multiform-validator.github.io/java/)