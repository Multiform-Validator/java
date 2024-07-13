> [!NOTE]
> I accept help to make the version of the other programming languages.

# Multiform-validator

## JAVA >=8

## Official Website

- [Multiform Validator - Website](https://multiformvalidator.netlify.app/documentation)

## Source code

- [Multiform-Validator](https://github.com/Multiform-Validator/java/)

## 📦 Download / Installation

follow the steps below to use the library in your project.

- [jitpack.io](https://jitpack.io/#Multiform-Validator/java) - Multiform Validator

<details>
  <summary>Gradle</summary>

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```gradle
dependencies {
        implementation 'com.github.Multiform-Validator:java:0.0.4'
}
```

</details>

<details>
  <summary>Maven</summary>

Step 1. Add the JitPack repository to your build file

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Step 2. Add the dependency

```xml

<dependency>
    <groupId>com.github.Multiform-Validator</groupId>
    <artifactId>java</artifactId>
    <version>0.0.4</version>
</dependency>
```

</details>

<details>
  <summary>Sbt</summary>

Step 1. Add the JitPack repository to your build file

Add it in your build.sbt at the end of resolvers:

```sbt
resolvers += "jitpack" at "https://jitpack.io"
```

Step 2. Add the dependency

```sbt
libraryDependencies += "com.github.Multiform-Validator" % "java" % "0.0.4"
```

</details>

<details>
  <summary>Leiningen</summary>

Step 1. Add the JitPack repository to your build file

Add it in your project.clj at the end of repositories:

```clojure
:repositories [["jitpack" "https://jitpack.io"]]
```

Step 2. Add the dependency

```clojure
:dependencies [[com.github.Multiform-Validator/java "0.0.4"]]
```

</details>

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
