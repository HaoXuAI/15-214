package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;

public class BinaryExpression implements Expression {

	private double num1;
	private double num2;
	private BinaryOperator binaryOperator;
	
	public BinaryExpression(Expression arg1, Expression arg2, BinaryOperator operator) {
		num1 = arg1.eval();
		num2 = arg2.eval();
		binaryOperator = operator;
	}
	
	
	@Override
	public double eval() {
		// TODO Auto-generated method stub
		return binaryOperator.apply(num1, num2);
	}

	@Override
	public String toString() {
		return binaryOperator.toString();
	}
}
