> [!NOTE]
> I accept help to make the version of the other programming languages.

# Multiform-validator

## JAVA

## How to install

follow the steps below to use the library in your project.

https://jitpack.io/#multiform-validator/java/0.0.1

## Example of how to use

```java
import io.github.multiform_validator.Validator;
import io.github.multiform_validator.EmailValidator;
import io.github.multiform_validator.CpfValidator;
import io.github.multiform_validator.CnpjValidator;

public class Main {
    public static void main(String[] args) {
        System.out.println(emailValidator.isEmail("foo@bar.com")); // true
        System.out.println(emailValidator.isEmail("foo@bar")); // false

        System.out.println(cpfValidator.cpfIsValid("123.456.789-09")); // true
        System.out.println(cpfValidator.cpfIsValid("123.456.789-00")); // false
        
        System.out.println(cnpjValidator.cnpjIsValid("12.345.678/0001-09")); // true
        System.out.println(cnpjValidator.cnpjIsValid("12.345.678/0001-00")); // false
    }
}
```

Lib is in development, there's other validators that you can use, but they are not yet documented.