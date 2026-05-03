# MiniTestFramework

A lightweight Java testing framework built using reflection and custom annotations.

## 🚀 Overview

MiniTestFramework is a simple implementation of a unit testing framework similar to JUnit. It supports running test methods, setup methods, basic assertions, and dependency injection.

## ✨ Features

* Run test methods using `@Test`
* Setup logic before each test using `@Before`
* Basic assertions with `assertEquals`
* Dependency Injection using `@Inject`
* Service registration using `@Service`
* Automatic class discovery via reflection

## 🧱 Project Structure

* `framework`
  Contains core framework logic and annotations
  (`@Test`, `@Before`, `@TestClass`, `@Inject`, `@Service`, `FWContext`, `Asserts`)

* `application`
  Example usage including test classes and services

## ▶️ How It Works

1. The framework scans for classes annotated with `@TestClass`
2. It creates instances of those classes
3. Dependencies are injected into fields marked with `@Inject`
4. Before each test:

    * Methods annotated with `@Before` are executed
5. Then:

    * Methods annotated with `@Test` are executed

## 🧪 Example

```java
@TestClass
public class MyTest {

    @Inject
    Calculator calculator;

    @Before
    public void init() {
        calculator.reset();
    }

    @Test
    public void testAdd() {
        assertEquals(calculator.add(3), 3);
    }
}
```

## ▶️ Run the Application

```bash
run Application.java
```

## 📌 Notes

* This project is for learning purposes
* It demonstrates how frameworks like JUnit and Spring work internally

## 🛠 Future Improvements

* Add `@After` annotation
* Test result reporting (pass/fail summary)
* Exception handling for failed tests
* Support multiple `@Before` methods

