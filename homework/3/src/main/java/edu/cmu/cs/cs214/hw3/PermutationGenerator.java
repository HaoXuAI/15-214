package edu.cmu.cs.cs214.hw3;

import edu.cmu.cs.cs214.hw3.PermutationInterface;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hao on 6/19/17.
 */
public class PermutationGenerator implements PermutationInterface {

    private char[] inputs;
    private int n;
    private ArrayList<String> res;

    public PermutationGenerator(char[] A) {
        res = new ArrayList<>();
        n = A.length;
        inputs = A;
    }

    public ArrayList<String> getPermutation() {
        permutate(n);
        return res;
    }

    public void permutate(int n) {
        if (n == 1) {
            res.add(Arrays.toString(inputs));
        } else {
            for (int i = 0; i < n - 1; i++) {
                permutate(n - 1);
                if (n % 2 == 0) {
                    swap(i, n - 1);
                } else {
                    //System.out.println(n);
                    swap(0, n - 1);
                }
            }
            permutate(n - 1);
        }
    }

    private void swap(int m, int n) {
        char temp = inputs[m];
        inputs[m] = inputs[n];
        inputs[n] = temp;
    }
}
