package edu.cmu.cs.cs214.hw2.operator;
import java.lang.Math;

public class AbsOperator implements UnaryOperator {

	@Override
	public double apply(double arg) {
		return Math.abs(arg);
	}
	@Override
	public String toString() {
		return "abs";
	}
}
