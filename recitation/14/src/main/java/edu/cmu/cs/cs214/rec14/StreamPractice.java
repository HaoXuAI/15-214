package edu.cmu.cs.cs214.rec14;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamPractice {

    /** Return number of lines in file, this is an example */
    public static int lineCountRegular(String fileName) throws IOException {
        int result = 0;
        try (Scanner sc = new Scanner(new File(fileName))) {
            while(sc.hasNextLine()) {
                result++;
                sc.nextLine();
            }
        }
        return result;
    }

    public static int lineCountStream(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            return (int) lines.count();
        }
    }

    private static boolean isPalindrome(String s) {
        int length = s.length();
        for (int i = 0, j = length - 1; i < length/2; i++, j--)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    /** Return palindromes in word list in original order
     * HINT:
     *
     * You can filter the contents of a stream
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-
     *
     * You can turn a stream into a Collection using Collectors
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
     */
    public static List<String> palindromesRegular(String wordListFileName) throws IOException {
        List<String> result = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(wordListFileName))) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (isPalindrome(line))
                    result.add(line);
            }
        }
        return result;
    }

    public static List<String> palindromesStream(String wordListFileName) throws IOException {
        // TODO: Implement me!
        return null;
    }

    /** Return number of palindromes in word list
     *
     * HINT:
     * Try combining the previous two methods
     */
    public static int palindromeCountRegular(String wordListFileName) throws IOException {
        int result = 0;
        try (Scanner sc = new Scanner(new File(wordListFileName))) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (isPalindrome(line)) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int palindromeCountStream(String wordListFileName) throws IOException {
        // TODO: Implement me!
        return 0;
    }

    /** Return longest palindrome in world list
     *  HINT:
     *  this might be helpful
     *  https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#max-java.util.Comparator-
     */
    public static String longestPalindromeRegular(String wordListFileName) throws IOException {
        String longest = "";
        try (Scanner sc = new Scanner(new File(wordListFileName))) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (isPalindrome(line) && line.length() > longest.length()) {
                    longest = line;
                }
            }
        }
        return longest;
    }

    public static String longestPalindromeStream(String wordListFileName) throws IOException {
        // TODO: Implement me!
        return null;
    }

    /** Returns the sum of all integers in the given collection satisfying the given criterion
     * HINT:
     *
     * You can get stream over a collection using this method:
     * https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#stream--
     *
     * Combine a stream into a single value using reduce:
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#reduce-T-java.util.function.BinaryOperator-
     */
    public static int sumAllSatisfyingRegular(Collection<Integer> numbers, Predicate<Integer> criterion) {
        int result = 0;
        for (int num : numbers)
            if (criterion.test(num))
                result += num;
        return result;
    }

    public static int sumAllSatisfyingStream(Collection<Integer> numbers, Predicate<Integer> criterion) {
        // TODO: Implement me!
        return 0;
    }


    /** Returns a frequency table describing the given collection
     *  HINTS:
     *
     *     You can merge a stream into a map using this Collector, which is analogous to Map.merge:
     *     https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toMap-java.util.function.Function-java.util.function.Function-java.util.function.BinaryOperator-
     *     Using this Collector requires three functions. While you could use lambdas for all three, the first
     *     can be found in java.util.function.Function, and the third can be found in java.lang.Integer.
     */
    public static <E> Map<E, Integer> frequencyTableRegular(Collection<E> coll) {
        Map<E, Integer> result = new HashMap<>();
        for (E e : coll)
            result.merge(e, 1, Integer::sum);
        return result;
    }

    public static <E> Map<E, Integer> frequencyTableStream(Collection<E> coll) {
        // TODO: Implement me!
        return null;
    }

    /** Returns a list consisting of all the characters in all the given strings, in order
     *  HINT:
     *  Use flatMap and friends to generate multiple elements from one element
     *  https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMap-java.util.function.Function-
     */
    public static List<Character> charactersInStringsRegular(String... strings) {
        List<Character> result = new ArrayList<>();
        for (String s : strings)
            for (char c : s.toCharArray())
                result.add(c);
        return result;
    }

    public static List<Character> charactersInStringsStream(String... strings) {
        // TODO: Implement me!
        return null;
    }
    
    /** Infinite Streams */
    public static Stream<BigInteger> positives() {
        BigInteger[] nextVal = { BigInteger.ONE };

        return Stream.generate(() -> {
            BigInteger result = nextVal[0];
            nextVal[0] = result.add(BigInteger.ONE);
            return result;
        });
    }

    public static Stream<BigInteger> fibonacciNumbers() {
        BigInteger[] fibs = { BigInteger.ZERO, BigInteger.ONE };
        // TODO: Implement me!
        return null;
    }

    public static BigInteger firstFibAbove(BigInteger threshold) {
        // TODO: Implement me!
        return null;
    }

    public static boolean isFib(BigInteger num) {
        // TODO: Implement me!
        return false;
    }

    /** the nth fibonacci number with 1 being the first one */
    public static BigInteger nthFib(int n) {
        // TODO: Implement me!
        return null;
    }
}
