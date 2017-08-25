package edu.cmu.cs.cs214.rec06.solver;

import java.util.List;
import java.util.Map;

/**
 * Solves a cryptarithm
 */
public interface CryptarithmSolver {
    /**
     * Given a cryptarithm, returns a list of solutions represented by a hash map.
     * returns an empty list if no solution is found or if cryptarithm not valid.
     *
     * @return solution to the cryptarithm, empty if cryptarithm not solvable.
     */
    List<Map<Character, Integer>> solve(String cryptarithm);
}
