package edu.cmu.cs.cs214.hw3;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.termcalc.ExpressionMaker;
import edu.cmu.cs.cs214.hw2.termcalc.ExpressionMakerImpl;
import edu.cmu.cs.cs214.hw2.termcalc.TerminalCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by hao on 6/28/17.
 */
public class Main {

    public static void main(String[] args) {

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
