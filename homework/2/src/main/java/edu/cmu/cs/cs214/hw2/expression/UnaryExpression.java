package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

public class UnaryExpression implements Expression {

	private double var;
	private UnaryOperator unaryOperator;
	
	public UnaryExpression(Expression operand, UnaryOperator operator) {
		var = operand.eval();
		unaryOperator = operator;
	}
	
	@Override
	public double eval() {
		// TODO Auto-generated method stub
		return unaryOperator.apply(var);
	}
	
	@Override
	public String toString() {
		return unaryOperator.toString();
	}

}
