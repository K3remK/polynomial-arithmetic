package com.kerem;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.kerem.polynomial.Polynomial;
import com.kerem.polynomial.StringToPolynomial;

public class Main {
    public static void main(String[] args) {
        try(FileWriter writer = new FileWriter("output.txt")) {
            Scanner reader = new Scanner(new File("input.txt"));
            int testCase = Integer.parseInt(reader.nextLine());
            for(int i = 0; i < testCase; i++) {
                String[] line = reader.nextLine().split(" ");
                String operator = line[0];
                Polynomial[] polynomials = new Polynomial[2];
                for(int j = 1; j < line.length; j++) {
                    polynomials[j - 1] = StringToPolynomial.toPolynomial(line[j]);
                }
                Polynomial result = Polynomial.calculate(operator, polynomials);
                writer.write(result.toString() + "\n");
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}