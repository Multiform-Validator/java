> [!NOTE]
> I accept help to make the version of the other programming languages.

# Multiform-validator

## JAVA >=8

## Official Website

- [Multiform Validator - Website](https://multiformvalidator.netlify.app/documentation)

## Source code

- [Multiform-Validator](https://github.com/Multiform-Validator/java/)

## How to install

follow the steps below to use the library in your project.

- [jitpack.io](https://jitpack.io/#Multiform-Validator/java) - Add the repository in your pom.xml

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml

<dependency>
    <groupId>com.github.multiform-validator</groupId>
    <artifactId>java</artifactId>
    <version>0.0.4</version>
</dependency>
```  

## Available methods - JAVA (0.0.4)v

- [CnpjValidator](https://multiform-validator.github.io/java/classes/CnpjValidator)

- [CpfValidator](https://multiform-validator.github.io/java/classes/CpfValidator)

- [CreditCardValidator](https://multiform-validator.github.io/java/classes/CreditCardValidator)

- [FileValidator](https://multiform-validator.github.io/java/classes/FileValidator)

- [Utils](https://multiform-validator.github.io/java/classes/Utils)

- [Validate](https://multiform-validator.github.io/java/classes/Validate)

- [Validator](https://multiform-validator.github.io/java/classes/Validator)

## Example how to use

### CnpjValidator

```java
// You can import the class or use the full path

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
