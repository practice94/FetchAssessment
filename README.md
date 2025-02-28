# Geolocation Test

## Overview
This project tests the geolocation functionality using Java and TestNG. It is a Maven-based project, making it easy to run tests via the command line.

## Prerequisites
- Java (JDK 8 or later)
- Maven

## Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/practice94/FetchSDET.git
   cd FetchSDET
   ```
2. Ensure Maven and Java are installed:
   ```sh
   mvn -version
   java -version
   ```

## Running Tests
To execute all tests, simply run:
```sh
mvn test
```

## Project Structure
- **src/main/java/**: Contains the main implementation files.
- **src/test/java/**: Contains the TestNG test cases.
- **pom.xml**: Project dependencies and configurations.

## Test Reports
After running the tests, reports will be available in the `target/surefire-reports` directory.

## Notes
- Ensure you have a stable internet connection if the tests require live API calls.


