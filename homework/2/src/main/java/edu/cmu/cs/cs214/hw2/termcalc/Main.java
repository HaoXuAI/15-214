package edu.cmu.cs.cs214.hw2.termcalc;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import java.util.Scanner;

/**
 * Main entry point for the command line calculator
 */
public class Main {
    /**
     * @param args program arguments
     */
    public static void main(String[] args) {
        //TODO: Replace null with an instance of your own ExpressionMaker
        ExpressionMaker expressionMaker = new ExpressionMakerImpl();
        TerminalCalculator calculator = new TerminalCalculator(expressionMaker);

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.print("Enter an expression: ");
                String expression = scanner.nextLine();
                try {
                    Expression exp = calculator.run(expression);
                    System.out.printf("Result: %s%n", exp.eval());
                } catch (Exception e) {
                    System.out.println("Input format not accepted. Please try again." );
                }
            } while(true);
        }
    }
}
