# Utils

The `Utils` class is a utility class with some methods to help you with some common tasks. It has the following
methods:

- getOnlyEmail
    - text (String) - required
    - getOnlyEmailWithOptions (options) - pass null if you don't want to use options
        - multiple (boolean) - default: false
        - cleanDomain (boolean | Arrays<String>) - default: false
        - repeatEmail (boolean) - default: false

## How to use

### getOnlyEmail

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

## Other classes validation methods

- [CnpjValidator](https://multiform-validator.github.io/java/classes/CnpjValidator)
- [CpfValidator](https://multiform-validator.github.io/java/classes/CpfValidator)
- [CreditCardValidator](https://multiform-validator.github.io/java/classes/CreditCardValidator)
- [FileValidator](https://multiform-validator.github.io/java/classes/FileValidator)
- [Validate](https://multiform-validator.github.io/java/classes/Validate)
- [Validator](https://multiform-validator.github.io/java/classes/Validator)

## Return to [Home](https://multiform-validator.github.io/java/)