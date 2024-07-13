# CpfValidator

The `CpfValidator` class is used to validate CPF numbers. It has a single method, `cpfIsValid`, which receives
a `String` as a parameter and returns a `boolean` value.

## How to use

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

## Other classes validation methods

- [CnpjValidator](https://multiform-validator.github.io/java/classes/CnpjValidator)
- [CreditCardValidator](https://multiform-validator.github.io/java/classes/CreditCardValidator)
- [FileValidator](https://multiform-validator.github.io/java/classes/FileValidator)
- [Utils](https://multiform-validator.github.io/java/classes/Utils)
- [Validate](https://multiform-validator.github.io/java/classes/Validate)
- [Validator](https://multiform-validator.github.io/java/classes/Validator)

## Return to [Home](https://multiform-validator.github.io/java/)