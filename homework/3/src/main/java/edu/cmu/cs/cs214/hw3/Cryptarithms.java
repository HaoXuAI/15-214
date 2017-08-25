package edu.cmu.cs.cs214.hw3;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumExpression;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Created by hao on 6/19/17.
 */
public class Cryptarithms {



    public Cryptarithms(String[] input) {

        String[] exp = input.split("=");
        char[] leftexp = exp[0].toCharArray();
        char[] rightexp = exp[1].toCharArray();

        HashSet<Character> set = new HashSet<>();
        for (char c : leftexp) {
            set.add(c);
        }
        for (char c : rightexp) {
            set.add(c);
        }

        char[] wholeChars = set.toString().toCharArray();
        PermutationInterface per = new PermutationGenerator(wholeChars);
        ArrayList<String> permutation = per.getPermutation();

    }
}
