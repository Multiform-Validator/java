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

### isAscii

```java
import static io.github.multiform_validator.Validator.isAscii;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isAscii("foo")); // true
        System.out.println(isAscii("bar")); // true
    }

    private static void invalid() {
        System.out.println(isAscii("こんにちは")); // false
        System.out.println(isAscii("안녕하세요")); // false
    }
}
```

### isCEP

```java
import static io.github.multiform_validator.Validator.isCEP;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isCEP("12345-678")); // true
        System.out.println(isCEP("12345678")); // true
    }

    private static void invalid() {
        System.out.println(isCEP("1234567844466")); // false
        System.out.println(isCEP("12345-6789")); // false
        System.out.println(isCEP("bcdedit")); // false
    }
}
```

### isDate

```java
import static io.github.multiform_validator.Validator.isDate;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isDate("2021-12-31")); // true
        System.out.println(isDate("2021-12-31 12:00:00")); // true
    }

    private static void invalid() {
        System.out.println(isDate("2021-12-32")); // false
    }
}
```

### isDecimal

```java
import static io.github.multiform_validator.Validator.isDecimal;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isDecimal("1.5")); // true
    }

    private static void invalid() {
        System.out.println(isDecimal("1.5.5")); // false
        System.out.println(isDecimal("1,5")); // false
        System.out.println(isDecimal("1")); // false
    }
}
```

### isEmail

```java
import static io.github.multiform_validator.Validator.isEmail;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isEmail("foo@bar.com")); // true
    }

    private static void invalid() {
        System.out.println(isEmail("foo@bar")); // false
        System.out.println(isEmail("foo@bar.")); // false
        System.out.println(isEmail("1foo@bar.com")); // false
    }
}
```

### isMACAddress

```java
import static io.github.multiform_validator.Validator.isMACAddress;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isMACAddress("00:00:00:00:00:00")); // true
    }

    private static void invalid() {
        System.out.println(isMACAddress("00:00:00:00:00:00:00")); // false
        System.out.println(isMACAddress("00:00:00:00:00:00:00:00")); // false
    }
}
```

### isNumber

```java
import static io.github.multiform_validator.Validator.isNumber;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isNumber("123")); // true
    }

    private static void invalid() {
        System.out.println(isNumber("123a")); // false
    }
}
```

### isPort

```java
import static io.github.multiform_validator.Validator.isPort;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isPort("8080")); // true
        System.out.println(isPort("80")); // true 
        System.out.println(isPort("65535")); // true
        System.out.println(isPort(80)); // true
        System.out.println(isPort(65535)); // true
    }

    private static void invalid() {
        System.out.println(isPort("8080a")); // false
        System.out.println(isPort("0")); // false
        System.out.println(isPort("65536")); // false
        System.out.println(isPort(0)); // false
        System.out.println(isPort(65536)); // false
    }
}
```

### isPostalCode

```java
import static io.github.multiform_validator.Validator.isPostalCode;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isPostalCode("12345-678")); // true
        System.out.println(isPostalCode("12345678")); // true
    }

    private static void invalid() {
        System.out.println(isPostalCode("1234567844466")); // false
        System.out.println(isPostalCode("12345-6789")); // false
        System.out.println(isPostalCode("bcdedit")); // false
    }
}
```

### isTime

```java
import static io.github.multiform_validator.Validator.isTime;

public class Main {
    public static void main(String[] args) {
        valid();
        invalid();
    }

    private static void valid() {
        System.out.println(isTime("12:00")); // true
    }

    private static void invalid() {
        System.out.println(isTime("12:00:00")); // false
        System.out.println(isTime("12:00:00:00")); // false
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