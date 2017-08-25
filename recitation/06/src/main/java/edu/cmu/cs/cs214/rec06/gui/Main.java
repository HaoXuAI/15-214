package edu.cmu.cs.cs214.rec06.gui;

import javax.swing.*;

import edu.cmu.cs.cs214.rec06.solver.CryptarithmSolver;
import edu.cmu.cs.cs214.rec06.solver.TheBestCryptarithmSolver;

/**
 * Entry point of your program
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            /*
               TODO:

               create an instance of your CryptarithmSolverGui backed by a new instance of
               TheBestCryptarithmSolver and make it visible. This requires a single line of code:

               https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#setVisible-boolean-

             */

             // Your code goes here
        	
        	CryptarithmSolver cry = new TheBestCryptarithmSolver();

        	CryptarithmSolverGui cryGui = new CryptarithmSolverGui(cry);
        });
    }
}
