The `CreditCardValidator` class is used to validate credit card numbers. It has two methods, `isCreditCardValid`
and `identifyCreditCard`.

## How to use

### isCreditCardValid

```java
import io.github.multiform_validator.CreditCardValidator;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }
    
    private static void valid() {
        System.out.println(CreditCardValidator.isCreditCardValid("4532 8770 0040 4166")); // true
    }
    
    private static void invalid() {
        System.out.println(CreditCardValidator.isCreditCardValid("4532 8770 0040 4167")); // false
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