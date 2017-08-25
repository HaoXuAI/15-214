package edu.cmu.cs.cs214.hw2.operator;
import java.lang.Math;

public class ExpOperator implements BinaryOperator {

	@Override
	public double apply(double arg1, double arg2) {
		return Math.pow(arg1, arg2);
	}

	@Override
	public String toString() {
		return "e";
	}
}
