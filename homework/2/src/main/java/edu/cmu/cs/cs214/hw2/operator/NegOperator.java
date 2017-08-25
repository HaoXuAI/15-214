package edu.cmu.cs.cs214.hw2.operator;

public class NegOperator implements UnaryOperator {

	@Override
	public double apply(double arg) {
		return - arg;
	}

	@Override
	public String toString() {
		return "Neg";
	}
}
