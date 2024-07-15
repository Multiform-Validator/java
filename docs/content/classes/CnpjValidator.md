The `CnpjValidator` class is used to validate CNPJ numbers. It has a single method, `cnpjIsValid`, which receives
a `String` as a parameter and returns a `boolean` value.

## How to use

```java
// You can import the class or use the full path

import io.github.multiform_validator.CnpjValidator;

public class Main {
    public static void main(String[] args) {
        System.out.println(cnpjIsTrue()); // true
        System.out.println(cnpjIsFalse()); // false
    }

    public static boolean cnpjIsTrue() {
        String cnpjTrue = "69.807.668/0001-41";
        return CnpjValidator.cnpjIsValid(cnpjTrue);
    }

    public static boolean cnpjIsFalse() {
        String cnpjFalse = "61.807.661/0001-48";
        return CnpjValidator.cnpjIsValid(cnpjFalse);
    }
}
```

## Other classes validation methods

- [CpfValidator](https://multiform-validator.github.io/java/classes/CpfValidator)
- [CreditCardValidator](https://multiform-validator.github.io/java/classes/CreditCardValidator)
- [FileValidator](https://multiform-validator.github.io/java/classes/FileValidator)
- [Utils](https://multiform-validator.github.io/java/classes/Utils)
- [Validate](https://multiform-validator.github.io/java/classes/Validate)
- [Validator](https://multiform-validator.github.io/java/classes/Validator)