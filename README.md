# Polynomial Arithmetic

A Java application for performing arithmetic operations (addition, subtraction, multiplication) on polynomials.

## Description

This project reads polynomial operations from an input file, performs the calculations, and writes the results to an output file. It supports variables, coefficients, and exponents.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Project

To build the project, run the following command in the project root directory:

```bash
mvn clean package
```

This will generate a JAR file in the `target/` directory (e.g., `target/polynomial-arithmetic-1.0-SNAPSHOT.jar`).

## Usage

1.  **Prepare Input File**: Create a file named `input.txt` in the same directory where you will run the application.

    **Input Format:**
    - The first line contains the number of test cases.
    - Each subsequent line represents a test case with the format: `[OPERATOR] [POLYNOMIAL_1] [POLYNOMIAL_2]`
    - Supported operators: `+` (addition), `-` (subtraction), `*` (multiplication).

    **Example `input.txt`:**
    ```text
    3
    - 5x3y2+3x2y4 5x2y4+2x
    + 2xyz+4y 2xyz-4y
    * x3+3x2y+3xy2+y3 x4+4x3y+6x2y2+4xy3+y4
    ```

2.  **Run the Application**:

    ```bash
    java -cp target/polynomial-arithmetic-1.0-SNAPSHOT.jar com.kerem.Main
    ```

3.  **Check Output**: The results will be written to `output.txt` in the same directory.

    **Example `output.txt`:**
    ```text
    5x3y2-2x2y4-2x
    4xyz
    x7+7x6y+21x5y2+35x4y3+35x3y4+21x2y5+7xy6+y7
    ```

## Project Structure

- `src/main/java`: Source code logic.
- `src/test`: Unit tests (if any).
- `pom.xml`: Maven project configuration.
