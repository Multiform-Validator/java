> [!NOTE]
> I accept help to make the version of the other programming languages.

# Multiform-validator

## JAVA

## How to install

follow the steps below to use the library in your project.

https://jitpack.io/#multiform-validator/java/

## Available methods - JAVA (0.0.2)v

- CnpjValidator
  - cnpjIsValid

- CpfValidator
  - cpfIsValid

- CreditCardValidator
  - isCreditCardValid
  - identifyCreditCard

- EmailValidator
  - isEmail

- Utils
  - getOnlyEmail
    - getOnlyEmailWithOptions (options)
      - multiple (boolean) - default: false
      - cleanDomain (boolean) - default: false
      - repeatEmail (boolean) - default: false

- Validator
  - isAscii
  - isCEP
  - isDate
  - isDecimal
  - isMACAddress
  - isNumber
  - isPort
  - isPostalCode
  - isTime


## How to use

### CnpjValidator
```java
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

### EmailValidator
```java
import static io.github.multiform_validator.EmailValidator.isEmail;

public class Main {
    public static void main(String[] args) {
        String email = "foo@bar.com";
        System.out.println(isEmail(email)); // true
    }
}
```

### Utils
```java
import io.github.multiform_validator.Utils;

public class Main {
    public static void main(String[] args) {
        String msg1 = "This is a message example with foo@bar.com email to test";
        System.out.println(Utils.getOnlyEmail(msg1)); // foo@bar.com

        String msg2 = "Example two foo1@bar.com and foo2@bar.com";
        // With options
        Utils.GetOnlyEmailOptionsParams options = new Utils.GetOnlyEmailOptionsParams();
        options.multiple = true;
        System.out.println(Utils.getOnlyEmailWithOptions(msg2, options)); // [foo1@bar.com, foo2@bar.com]
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

    public void validMethods () {
        System.out.println(Validator.isAscii("foo")); // true
        System.out.println(Validator.isCEP("12345-678")); // true
        System.out.println(Validator.isDate("2021-01-01")); // true
        System.out.println(Validator.isDecimal("1.5")); // true
        System.out.println(Validator.isMACAddress("00:00:00:00:00:00")); // true
        System.out.println(Validator.isNumber("123")); // true
        System.out.println(Validator.isPort("8080")); // true
        System.out.println(Validator.isPostalCode("12345-678")); // true
        System.out.println(Validator.isTime("12:00")); // true
    }

    public void invalidMethods () {
        System.out.println(Validator.isAscii("こんにちは")); // false
        System.out.println(Validator.isCEP("12345678")); // false
        System.out.println(Validator.isDate("2021-01-32")); // false
        System.out.println(Validator.isDecimal("1.5.5")); // false
        System.out.println(Validator.isMACAddress("00:00:00:00:00:00:00")); // false
        System.out.println(Validator.isNumber("123a")); // false
        System.out.println(Validator.isPort("8080a")); // false
        System.out.println(Validator.isPostalCode("12345678")); // false
        System.out.println(Validator.isTime("12:00:00")); // false
    }
}
```

Lib is in development, there's other validators that you can use, but they are not yet documented.
