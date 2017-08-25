package edu.cmu.cs.cs214.hw3;


import java.util.ArrayList;

/**
 * The Permutation generator interface using Strategy Pattern
 *
 */
public interface PermutationInterface {

    public void permutate(int n);

    public ArrayList<String> getPermutation();

}
