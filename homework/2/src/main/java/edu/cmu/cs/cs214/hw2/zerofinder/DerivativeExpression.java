package edu.cmu.cs.cs214.hw2.zerofinder;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.termcalc.*;

public class DerivativeExpression implements Expression {

	private double var;
	private static final double DELTA_X = 1e-9;
	private Expression newExpression1;
	private Expression newExpression2;
	
	/**
	   * Creates an expression representing the derivative of the specified
	   * function with respect to the specified variable.
	   *
	   * @param fn the function whose derivative this expression represents
	   * @param independentVar the variable with respect to which we’re
	   *   differentiating
	   */
	  public DerivativeExpression(Expression fn,
	                              VariableExpression indepedentVar) {
		  var = indepedentVar.eval();
		  newExpression1 = convertHelper(fn.toString(), indepedentVar.name(), var);
		  var += DELTA_X;
		  newExpression2 = convertHelper(fn.toString(), indepedentVar.name(), var);
	  }
	
	  public Expression convertHelper(String fn, String x, double var) {

		  CalculatorImpl calculator = new CalculatorImpl();
		  
		  Expression expression = calculator.getCalculator(fn, x, var);
		  return expression;
	  }
	
	@Override
	public double eval() {
		// TODO Auto-generated method stub
		return (newExpression2.eval() - newExpression1.eval()) / DELTA_X;
	}
	
	@Override
	public String toString() {
		return null;
	}

}
