# QA Automation Framework

This is a Maven-based Java project for QA Automation using Selenium WebDriver, JUnit 5, and Allure reporting.

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- Chrome browser (for running tests)

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/qa/automation/
│           ├── pages/         # Page Object classes
│           └── utils/         # Utility classes
└── test/
    └── java/
        └── com/qa/automation/
            └── tests/         # Test classes
```

## Setup

1. Clone the repository
2. Run `mvn clean install` to download dependencies and build the project

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run tests with Allure report generation
```bash
mvn clean test allure:report
```

### View Allure report
```bash
mvn allure:serve
```

## Configuration

- `src/main/resources/log4j2.xml` - Logging configuration
- `src/test/resources/allure.properties` - Allure reporting configuration

## Features

- Page Object Model design pattern
- Automatic browser driver management with WebDriverManager
- Fluent assertions with AssertJ
- Comprehensive logging with SLF4J
- Detailed test reporting with Allure
- CI/CD ready with Bamboo support

## Best Practices

1. Follow Page Object Model pattern for all page interactions
2. Use descriptive test names and proper test annotations
3. Implement proper wait strategies for element interactions
4. Use AssertJ for fluent and readable assertions
5. Add proper logging for better debugging
6. Follow clean code principles and maintain proper documentation 