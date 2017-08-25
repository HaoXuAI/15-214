package edu.cmu.cs.cs214.hw2.zerofinder;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.termcalc.CalculatorImpl;

/**
 * Finds zeros of arbitrary functions using Newton's method.
 */
public class ZeroFinder {
	// TODO: Write the zero method

	private static double zero;

	private static Expression fnExpression;

	/**
	 * Returns a zero of the specified function using Newton’s method with
	 * approxZero as the initial estimate.
	 *
	 * @param fn
	 *            the function whose zero is to be found
	 * @param x
	 *            the independent variable of the function
	 * @param approxZero
	 *            initial approximation for the zero of the function
	 * @param tolerance
	 *            how close to zero f(the returned value) has to be
	 * @return a value x for which f(x) is "close to zero" (<= tolerance)
	 */
	public static double zero(Expression fn, VariableExpression x, double approxZero, double tolerance) {
		x.store(approxZero);
		zero = approxZero;
		fnExpression = convertHelper(fn.toString(), x.name(), approxZero);

		while (fnExpression.eval() > tolerance) {
			fnExpression = convertHelper(fn.toString(), x.name(), zero);
			x.store(zero);
			DerivativeExpression derivate = new DerivativeExpression(fn, x);
			zero = zero - fnExpression.eval() / derivate.eval();
			fnExpression = convertHelper(fn.toString(), x.name(), zero);
		}
		
		return zero;
	}

	private static Expression convertHelper(String fn, String x, double var) {

		CalculatorImpl calculator = new CalculatorImpl();

		Expression expression = calculator.getCalculator(fn, x, var);
		return expression;
	}
}
