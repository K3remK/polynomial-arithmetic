package com.kerem.polynomial;


/**
 * This class is used for representing the variables in a polynomial term as a variable object.
 */
public class Variable {
    char variable;
    int degree;
    
    public Variable(char variable, int degree) { 
        this.variable = variable;
        this.degree = degree;
    }
}
