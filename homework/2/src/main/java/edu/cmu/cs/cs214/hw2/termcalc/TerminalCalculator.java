package edu.cmu.cs.cs214.hw2.termcalc;
import java.util.Objects;
import edu.cmu.cs.cs214.hw2.expression.Expression;

/**
 * TerminalCalculator - a command line calculator.
 */
public class TerminalCalculator {
    //CHECKSTYLE:OFF
    private final ExpressionParser parser;

    public TerminalCalculator(ExpressionMaker maker) {
        Objects.requireNonNull(maker);
        parser = new ExpressionParser(maker);
    }

    public Expression run(String expression) {
        return parser.eval(expression);
    }
}
