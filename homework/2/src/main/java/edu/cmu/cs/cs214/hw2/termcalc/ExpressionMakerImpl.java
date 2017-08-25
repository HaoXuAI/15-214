package edu.cmu.cs.cs214.hw2.termcalc;

import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumExpression;
import edu.cmu.cs.cs214.hw2.expression.UnaryExpression;
import edu.cmu.cs.cs214.hw2.operator.*;

public class ExpressionMakerImpl implements ExpressionMaker {

	@Override
	public Expression sumExpression(Expression addend1, Expression addend2) {
		// TODO Auto-generated method stub
		Expression newExpression = new BinaryExpression(addend1, addend2, new AddOperator());
		return newExpression;
	}

	@Override
	public Expression differenceExpression(Expression op1, Expression op2) {
		// TODO Auto-generated method stub
		Expression newExpression = new BinaryExpression(op1, op2, new SubOperator());
		return newExpression;
	}

	@Override
	public Expression productExpression(Expression factor1, Expression factor2) {
		// TODO Auto-generated method stub
		Expression newExpression = new BinaryExpression(factor1, factor2, new MulOperator());
		return newExpression;
	}

	@Override
	public Expression divisionExpression(Expression dividend, Expression divisor) {
		// TODO Auto-generated method stub
		Expression newExpression = new BinaryExpression(dividend, divisor, new DivOperator());
		return newExpression;
	}

	@Override
	public Expression exponentiationExpression(Expression base, Expression exponent) {
		// TODO Auto-generated method stub
		Expression newExpression = new BinaryExpression(base, exponent, new ExpOperator());
		return newExpression;
	}

	@Override
	public Expression negationExpression(Expression operand) {
		// TODO Auto-generated method stub
		Expression newExpression = new UnaryExpression(operand, new NegOperator());
		return newExpression;
	}

	@Override
	public Expression absoluteValueExpression(Expression value) {
		// TODO Auto-generated method stub
		Expression newExpression = new UnaryExpression(value, new AbsOperator());
		return newExpression;
	}

	@Override
	public Expression numberExpression(double value) {
		// TODO Auto-generated method stub
		return new NumExpression(value);
	}

}
