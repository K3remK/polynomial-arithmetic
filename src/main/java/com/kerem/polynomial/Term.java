package com.kerem.polynomial;

/**
 * This class is used to hold the coefficient and the variables of a polynomial term as a term object.
 */
public class Term {
    int coefficient;
    Variable[] variables;

    public Term(int coefficient, Variable[] variables) {
        this.coefficient = coefficient;
        this.variables = variables;
    }
}
