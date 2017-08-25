package edu.cmu.cs.cs214.hw2.operator;

/**
 * BinaryOperator - an arithmetic operator with two operands.
 */
public interface BinaryOperator extends Operator {
	/**
	 * Applies the Operator on the two numbers given.
	 * 
	 * @param arg1 the first number before the operator
	 * @param arg2 the second number after the operator
	 * @return the output of the operation given inputs arg1 and arg2
	 */
	double apply(double arg1, double arg2);
}
