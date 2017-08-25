package edu.cmu.cs.cs214.rec06.solver;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * The best cryptarithm solver implementation ever!
 * We lied; actually it returns a bogus solution to every cryptarithm.
 */
public class TheBestCryptarithmSolver implements CryptarithmSolver {
    public List<Map<Character, Integer>> solve(String cryptarithm) {
        Set<Character> characters = new HashSet<>();
        for (char c : cryptarithm.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                characters.add(c);
            }
        }

        //If more than 10 characters, just return an empty list
        List<Map<Character, Integer>> result = new ArrayList<>();
        if (characters.size() > 10) {
            return result;
        }

        Map<Character, Integer> solution = new HashMap<>();
        for (Character c : characters) {
            solution.put(c, 0);
        }
        result.add(solution);
        return result;
    }
}
