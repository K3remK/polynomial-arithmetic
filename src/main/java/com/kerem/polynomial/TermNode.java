package com.kerem.polynomial;

/**
 * This class is used for representing each node on a linked-list polynomial and it holds the {@link Term} object as data.
 */
public class TermNode {
    protected Term data;
    protected TermNode next;

    public TermNode(Term data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Sets the next TermNode.
     * @param next new next
     */
    public void setNext(TermNode next){
        this.next = next;
    }

    /**
     * Returns the next TermNode.
     * @return the next TermNode.
     */
    public TermNode getNext(){
        return next;
    }

    /**
     * Represents the TermNode as String.
     * @return the string representation of the TermNode.
     */
    @Override
    public String toString(){
        StringBuilder term = new StringBuilder();
        int coefficient = data.coefficient;
        Variable[] variables = data.variables;

        if(coefficient == -1)
            term.append("-");
        else if(coefficient != 1)
            term.append(coefficient);

        for(Variable variable : variables) {
            if(variable.degree != 1 && variable.degree != 0) {
                term.append(variable.variable).append(variable.degree);
            } else if(variable.degree == 1) {
                term.append(variable.variable);
            }
        }
        return term.toString();
    }


}
