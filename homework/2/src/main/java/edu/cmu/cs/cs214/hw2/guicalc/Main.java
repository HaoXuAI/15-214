package edu.cmu.cs.cs214.hw2.guicalc;

import edu.cmu.cs.cs214.hw2.operator.AbsOperator;
import edu.cmu.cs.cs214.hw2.operator.AddOperator;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.DivOperator;
import edu.cmu.cs.cs214.hw2.operator.MulOperator;
import edu.cmu.cs.cs214.hw2.operator.NegOperator;
import edu.cmu.cs.cs214.hw2.operator.SubOperator;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Main program that runs the GUI Calculator
 */
public class Main {
	/**
	 * The main method to run the GUI Calculator
	 * @param args : do not take in any arguments from the command line
	 */
    public static void main(String[] args) {
        // TODO: Replace null with your own unary operators. Use a Linked HashSet so operators are displayed in order.
        Set<UnaryOperator> unaryOperators = new LinkedHashSet<UnaryOperator>();
        unaryOperators.add(new NegOperator());
        unaryOperators.add(new AbsOperator());
        
        // TODO: Replace null with your own binary operators. Use a Linked HashSet so operators are displayed in order
        Set<BinaryOperator> binaryOperators = new LinkedHashSet<BinaryOperator>();
        binaryOperators.add(new AddOperator());
        binaryOperators.add(new DivOperator());
        binaryOperators.add(new MulOperator());
        binaryOperators.add(new SubOperator());
        
        // Run the calculator!
        new GuiCalculator(unaryOperators, binaryOperators);
    }
}
