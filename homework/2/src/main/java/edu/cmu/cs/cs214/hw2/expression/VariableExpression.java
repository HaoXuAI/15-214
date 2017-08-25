package edu.cmu.cs.cs214.hw2.expression;

/**
 * An expression that represents a variable. Its value may be set as well as
 * read. Unlike other expressions in this assignment, variables are mutable.
 * Using a variable within a containing expression makes the expression a
 * function of the current value of the variable.
 *
 * <p>
 * Variables have immutable string names, which are used in their string
 * representations. If an expression contains multiple distinct variables as
 * subexpressions, it's important that they have different names, or the string
 * representation of the containing expression will be misleading.
 */
public class VariableExpression implements Expression {

	private double var;
	private String newName;

	/**
	 * Constructs a variable with the specified name, whose initial value is
	 * zero.
	 *
	 * @param name
	 *            name of this variable, must be different from the name of
	 *            other variables in the same expression.
	 */
	public VariableExpression(String name) {
		// TODO: Complete this method.
		this.newName = name;
		this.var = 0.0;
	}

	@Override
	public double eval() {
		// TODO: Complete this method.
		return this.var;
	}

	@Override
	public String toString() {
		// TODO: Complete this method.
		return this.newName;
	}

	/**
	 * Sets the value of this variable.
	 *
	 * @param value
	 *            the new value of this variable
	 */
	public void store(double value) {
		// TODO: Complete this method.
		this.var = value;
	}

	/**
	 * @return the name of this variable
	 */
	public String name() {
		// TODO: Complete this method.
		return this.toString();
	}
}
