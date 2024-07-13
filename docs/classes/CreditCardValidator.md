# CreditCardValidator

The `CreditCardValidator` class is used to validate credit card numbers. It has two methods, `isCreditCardValid`
and `identifyCreditCard`.

## How to use

### isCreditCardValid

```java
import io.github.multiform_validator.CreditCardValidator;

public class Main {
    public static void main(String[] args) {
        String creditCardTrue = "4532 8770 0040 4166";
        String creditCardFalse = "4532 8770 0040 4167";
        System.out.println(CreditCardValidator.isCreditCardValid(creditCardTrue)); // true
        System.out.println(CreditCardValidator.isCreditCardValid(creditCardFalse)); // false
    }
}
```

### identifyCreditCard

```java
import io.github.multiform_validator.CreditCardValidator;

public class Main {
    public static void main(String[] args) {
        String creditCard = "4532 8770 0040 4166";
        System.out.println(CreditCardValidator.identifyCreditCard(creditCard)); // Visa
    }
}
```

## Other classes validation methods

- [CnpjValidator](https://multiform-validator.github.io/java/classes/CnpjValidator)
- [CpfValidator](https://multiform-validator.github.io/java/classes/CpfValidator)
- [FileValidator](https://multiform-validator.github.io/java/classes/FileValidator)
- [Utils](https://multiform-validator.github.io/java/classes/Utils)
- [Validate](https://multiform-validator.github.io/java/classes/Validate)
- [Validator](https://multiform-validator.github.io/java/classes/Validator)

## Return to [Home](https://multiform-validator.github.io/java/)