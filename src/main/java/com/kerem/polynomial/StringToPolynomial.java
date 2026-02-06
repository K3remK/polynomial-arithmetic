package com.kerem.polynomial;

import java.util.Arrays;

/**
 * This class is useful for a transition from the string representation of a polynomial to a linked-list.
 * The function {@link #toPolynomial(String)} is used for that purpose.
 */

public class StringToPolynomial {
    /**
     * This function takes a list of String terms of a polynomial and converts them into a linked-list polynomial using the functions
     * {@link #getCoefficient(String)}, {@link #getVariables(String)} and {@link #getTerms(String)}.
     *
     * @param polyString the list of string terms which the original polynomial has.
     * @return the linked-list version of the string polynomial
     */
    public static Polynomial toPolynomial(String polyString) {
        Polynomial result = new Polynomial();
        String[] terms = getTerms(polyString);
        for(String term : terms) {
            result.insertLast(new TermNode(new Term(getCoefficient(term), getVariables(term))));
        }
        return result;
    }

    /**
     * This function takes a String term and pulls out the variables and their degrees.
     *
     * @param term the string term that contains the variables.
     * @return an array of {@link Variable} class version of the variables
     */
    private static Variable[] getVariables(String term) {
        Variable[] variables = new Variable[3];
        int variableCount = 0;
        for(int i = 0; i < term.length(); i++) {
            char variable = term.charAt(i);
            if(Character.isAlphabetic(variable)) {
                StringBuilder degree = new StringBuilder();
                int k = i + 1;
                //while loop for multiple digit degree value.
                while(k < term.length() && Character.isDigit(term.charAt(k))) {
                    degree.append(term.charAt(k++));
                }
                variables[variableCount++] = new Variable(variable, (degree.isEmpty() ? 1 : Integer.parseInt(degree.toString())));
                i = k - 1;
            }
        }
        return Arrays.copyOf(variables, variableCount); //remove the null members of variable lists
    }

    /**
     * This function takes a string term and returns its coefficient.
     * @param term the string term.
     * @return the coefficient of the term.
     */
    private static int getCoefficient(String term) {
        String sign = "+";
        StringBuilder coefficient = new StringBuilder();
        int i = 0;
        if(term.startsWith("-")) {
            sign = "-";
            i++;
        }
        //while loop for multiple digit coefficient value.
        while (i < term.length() && Character.isDigit(term.charAt(i))) {
            coefficient.append(term.charAt(i++));
        }
        return Integer.parseInt(coefficient.isEmpty() ? (sign + 1) : coefficient.insert(0, sign).toString());
    }

    /**
     * This function takes a string polynomial and splits it into its terms.
     * @param polyString a string polynomial.
     * @return an array of string terms.
     */
    private static String[] getTerms(String polyString) {
        String[] terms;
        //if polynomial starts with "-" we need to remove it first for not to get an empty string since we are using split function.
        if(polyString.startsWith("-")) {
            polyString = polyString.substring(1);
            terms = polyString.split("\\+|-");
            terms[0] = "-" + terms[0];
        } else {
            terms = polyString.split("\\+|-");
        }
        int termCount = 1;
        //for loop is to determine whether a term is negative or not, we need to do this since we used split function.
        for(int i = 0; i < polyString.length(); i++) {
            if(polyString.charAt(i) == '-' || polyString.charAt(i) == '+') {
                terms[termCount] = polyString.charAt(i) == '-' ? "-" + terms[termCount++] : terms[termCount++];
            }
        }
        return terms;
    }
}
