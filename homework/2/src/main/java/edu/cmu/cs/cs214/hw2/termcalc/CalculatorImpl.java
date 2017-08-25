package edu.cmu.cs.cs214.hw2.termcalc;

import edu.cmu.cs.cs214.hw2.expression.Expression;

public class CalculatorImpl {
	
	
	public Expression getCalculator(String str) {
		ExpressionMaker expressionMaker = new ExpressionMakerImpl();
		TerminalCalculator calculator = new TerminalCalculator(expressionMaker);
		return calculator.run(str);
	};
	
	public Expression getCalculator(String fn, String x, double var) {
		ExpressionMaker expressionMaker = new ExpressionMakerImpl();
		TerminalCalculator calculator = new TerminalCalculator(expressionMaker);
		String newString = fn.replaceAll(x, Double.toString(var));
		return calculator.run(newString);
	};
}
