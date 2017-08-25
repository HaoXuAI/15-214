package edu.cmu.cs.cs214.hw2.expression;

public class NumExpression implements Expression {

	private double var;
	
	public NumExpression(double val) {
		var = val;
	}
	
	@Override
	public double eval() {
		// TODO Auto-generated method stub
		return var;
	}

	@Override
	public String toString() {
		return Double.toString(var);
	}
}
