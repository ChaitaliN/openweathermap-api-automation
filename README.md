# OpenWeatherMap API automation

- [OpenWeatherMap API understanding](./docs/openweathermap.md)
- [Notes and Assumptions](./docs/openweathermap.md)

## Prerequisite

- Java 1.8
- Maven 3.6

## Framework

- Testing approach - BDD

  - [Feature files](./src/test/resources/feature/)
  - [Step definitions](./src/test/java/stepDefinition)

- BDD tool - Cucumber
- Test tool - JUnit
- Build tool - Maven
- Configuration management - [Properties file](./src/test/resources/test.config.properties)

## Usage

- Run all scenarios

  ```sh
  mvn clean test

  ```

## Reports

- HTML report

  ```sh
  target/cucumber-html-report/index.html
  ```

- Json report

  ```sh
  target/cucumber.json
  ```

- Scenario report

  ```sh
  target/cucumber-pretty.txt
  ```

- XML report

  ```sh
  target/cucumber-results.xml
  ```
