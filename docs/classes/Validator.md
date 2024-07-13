# Validator

The `Validator` class is used to validate some common data types. It has the following methods:

- isAscii
    - value (String) - required
- isCEP
    - value (String) - required
- isDate
    - value (String) - required
- isDecimal
    - value (String) - required
- isEmail
    - value (String) - required
- isMACAddress
    - value (String) - required
- isNumber
    - value (String) - required
- isPort
    - value (String) - required
- isPostalCode
    - value (String) - required
- isTime
    - value (String) - required

## How to use

### General example

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

## Other classes validation methods

- [CnpjValidator](https://multiform-validator.github.io/java/classes/CnpjValidator)
- [CpfValidator](https://multiform-validator.github.io/java/classes/CpfValidator)
- [CreditCardValidator](https://multiform-validator.github.io/java/classes/CreditCardValidator)
- [FileValidator](https://multiform-validator.github.io/java/classes/FileValidator)
- [Utils](https://multiform-validator.github.io/java/classes/Utils)
- [Validate](https://multiform-validator.github.io/java/classes/Validate)

## Return to [Home](https://multiform-validator.github.io/java/)