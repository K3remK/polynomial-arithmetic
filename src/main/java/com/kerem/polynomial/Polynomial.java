package com.kerem.polynomial;

import java.util.Arrays;

/**
 * This class is used for representing polynomials as linked list.
 * It has useful functions to perform various operation on polynomials such as;
 * {@link #add(Polynomial)}, {@link #subtract(Polynomial)}, {@link #multiply(Polynomial)}, {@link #calculate(String, Polynomial[])},
 * {@link #insertLast(TermNode)}, {@link #toString()}.
 */

public class Polynomial {

    protected TermNode head;
    protected TermNode tail;

    public Polynomial() {
        head = null;
        tail = null;
    }

    /**
     * This function takes an operator and a list of polynomials to be able to perform a mathematical operation between them.
     *
     * @param operator    the mathematical operation to be performed.
     * @param polynomials the two polynomial to be used.
     * @return the result of the mathematical operation.
     */
    public static Polynomial calculate(String operator, Polynomial[] polynomials) {
        return switch (operator) {
            case "+" -> polynomials[0].add(polynomials[1]);
            case "-" -> polynomials[0].subtract(polynomials[1]);
            case "*" -> polynomials[0].multiply(polynomials[1]);
            default -> throw new IllegalArgumentException("Operator argument is not valid!");
        };
    }

    /**
     * This function adds two polynomial together with respect to its terms. x > y > z in terms of precedence.
     *
     * @param polynomial the polynomial to be added to "this" polynomial.
     * @return the result of addition as a {@link Polynomial}.
     */
    public Polynomial add(Polynomial polynomial) {
        TermNode i = head, j = polynomial.head;
        Polynomial result = new Polynomial();
        //this while loop travels through the polynomials terms.
        while (i != null && j != null) {
            if (checkVariables(i.data.variables, j.data.variables)) {
                int coefficient = j.data.coefficient + i.data.coefficient;
                if (coefficient != 0) { // if the coefficient becomes 0 after the operation no need add it to result.
                    result.insertLast(new TermNode(new Term(coefficient, i.data.variables)));
                }
                i = i.next;
                j = j.next;
            } else if (checkPrecedence(i, j) == i) {
                result.insertLast(i);
                i = i.next;
            } else {
                result.insertLast(j);
                j = j.next;
            }
        }
        //add the remaining nodes to the result.
        while (i != null) {
            result.insertLast(i);
            i = i.next;
        }
        while (j != null) {
            result.insertLast(j);
            j = j.next;
        }
        return result;
    }

    /**
     * This function takes two {@link TermNode} variable and compares their precedence by checking the variables and degrees.
     *
     * @param i first term node
     * @param j second term node
     * @return returns the higher precedence term node.
     */
    private TermNode checkPrecedence(TermNode i, TermNode j) {
        int m = 0;
        while (m < i.data.variables.length && m < j.data.variables.length) {
            Variable var1 = i.data.variables[m];
            Variable var2 = j.data.variables[m];
            //the comparison between the 'char' variables
            //lesser char value means high precedence since x:120, y:121, z:122 in integer value
            if (var1.variable == var2.variable) {
                if (var1.degree > var2.degree) return i;
                else if (var1.degree < var2.degree) return j;
                else m++;
            } else if (var1.variable < var2.variable) return i;
            else return j;
        }
        return null;
    }

    /**
     * This function takes two {@link Variable} array and compares them in terms of equality.
     * The variables are equal if
     * the variable names and degrees are equal.
     *
     * @param v1 the first {@link Variable} array.
     * @param v2 the second {@link Variable} array.
     * @return true if variables are equal.
     */
    private boolean checkVariables(Variable[] v1, Variable[] v2) {
        if (v1.length != v2.length) return false;
        else {
            // checks the equality between variables and degrees if not equal returns false
            for (int i = 0; i < v1.length; i++) {
                if (v1[i].variable != v2[i].variable || v1[i].degree != v2[i].degree) return false;
            }
        }
        return true;
    }

    /**
     * This function subtracts two polynomials with respect to its terms. x > y > z in terms of precedence.
     *
     * @param polynomial the polynomial to be subtracted from "this" one.
     * @return the result of subtraction as a {@link Polynomial}.
     */
    public Polynomial subtract(Polynomial polynomial) {
        TermNode i = head, j = polynomial.head;
        Polynomial result = new Polynomial();
        while (i != null && j != null) {
            if (checkVariables(i.data.variables, j.data.variables)) {
                j.data.coefficient *= -1;
                int coefficient = j.data.coefficient + i.data.coefficient;
                if (coefficient != 0) // if the coefficient becomes 0 after the operation no need add it to result.
                    result.insertLast(new TermNode(new Term(coefficient, i.data.variables)));
                i = i.next;
                j = j.next;
            } else if (checkPrecedence(i, j) == i) {
                result.insertLast(i);
                i = i.next;
            } else {
                i.data.coefficient *= -1;
                result.insertLast(j);
                j = j.next;
            }
        }
        //add the remaining nodes to the result.
        while (i != null) {
            result.insertLast(i);
            i = i.next;
        }
        while (j != null) {
            j.data.coefficient *= -1;
            result.insertLast(j);
            j = j.next;
        }
        return result;
    }

    /**
     * This function multiplies two polynomial together.
     *
     * @param polynomial the polynomial to be multiplied with "this" one.
     * @return the result of multiplication as a {@link Polynomial}.
     */
    public Polynomial multiply(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        TermNode i = head, j = polynomial.head, k;
        //travel through the nodes of first polynomial
        while (i != null) {
            Polynomial tmp = new Polynomial();
            k = j;
            //travel through the nodes of second polynomial.
            while (k != null) {
                tmp.insertLast(new TermNode(new Term(i.data.coefficient * k.data.coefficient,
                        mergeVariables(i.data.variables, k.data.variables))));
                k = k.next;
            }
            result = result.add(tmp); // each iteration creates a temporary polynomial
            // by multiplying a node from the first polynomial with all the nodes of second polynomial.
            // then we simply add it to the main result.
            i = i.next;
        }
        return result;
    }

    /**
     * This function takes two arrays of {@link Variable} and merges the arrays as if it performs a multiplication between the variables.
     *
     * @param v1 the first array of variables.
     * @param v2 the second array of variables.
     * @return the merged {@link Variable} array.
     */
    private Variable[] mergeVariables(Variable[] v1, Variable[] v2) {
        Variable[] result = new Variable[3];
        int i = 0, j = 0, varCount = 0;
        //travel through the variable list and merge them.
        while (i < v1.length && j < v2.length) {
            if (v1[i].variable == v2[j].variable)
                result[varCount++] = new Variable(v1[i].variable, v1[i++].degree + v2[j++].degree);
            else if (v1[i].variable < v2[j].variable)
                result[varCount++] = v1[i++];
            else
                result[varCount++] = v2[j++];
        }
        //add the remaining variables
        while (i < v1.length) {
            result[varCount++] = v1[i++];
        }
        while (j < v2.length) {
            result[varCount++] = v2[j++];
        }
        return Arrays.copyOf(result, varCount); //remove the null members of the variable list.
    }

    /**
     * This function adds a new term at the end of the polynomial linked-list.
     *
     * @param newTermNode the term to be added as {@link TermNode}.
     */
    public void insertLast(TermNode newTermNode) {
        if (head == null) {
            head = newTermNode;
        } else {
            tail.setNext(newTermNode);
        }
        tail = newTermNode;
    }

    /**
     * Represents the polynomial as string.
     *
     * @return the {@link String} version of a {@link Polynomial}.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        TermNode tmp = head;
        while (tmp != null) {
            if (tmp == head) {
                result.append(tmp);
            } else {
                if (tmp.data.coefficient > 0)
                    result.append("+").append(tmp);
                else
                    result.append(tmp);
            }
            tmp = tmp.getNext();
        }
        return result.isEmpty() ? "0" : result.toString();
    }
}
