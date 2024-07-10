> [!NOTE]
> I accept help to make the version of the other programming languages.

# Multiform-validator

## JAVA

## How to install

follow the steps below to use the library in your project.

https://jitpack.io/#multiform-validator/java/

## Example of how to use

```java
import io.github.multiform_validator.Validator;
import io.github.multiform_validator.EmailValidator;
import io.github.multiform_validator.CpfValidator;
import io.github.multiform_validator.CnpjValidator;
import io.github.multiform_validator.Utils;

public class Main {
    public static void main(String[] args) {
        System.out.println(EmailValidator.isEmail("foo@bar.com")); // true
        System.out.println(EmailValidator.isEmail("foo@bar")); // false

        System.out.println(CpfValidator.cpfIsValid("123.456.789-09")); // true
        System.out.println(CpfValidator.cpfIsValid("123.456.789-00")); // false
        
        System.out.println(CnpjValidator.cnpjIsValid("12.345.678/0001-09")); // true
        System.out.println(CnpjValidator.cnpjIsValid("12.345.678/0001-00")); // false

        System.out.println(Validator.isAscii("foo")); // true
        System.out.println(Validator.isAscii("foo©")); // false
        
        System.out.println(Utils.getOnlyEmail("This is an example test1@email.com bla yes my friend loren ipsun")); // test1@email.com
        System.out.println(Utils.getOnlyEmail("This is an example bla yes my friend loren ipsun")); // null
        // With options
        Utils.GetOnlyEmailOptionsParams options = new Utils.GetOnlyEmailOptionsParams();
        options.multiple = true;
        System.out.println(Utils.getOnlyEmail("This is an example test1@example.com bla test2@example.com yes yes", options)); // [test1@example.com, test2@example.com]
    }
}
```

Lib is in development, there's other validators that you can use, but they are not yet documented.
