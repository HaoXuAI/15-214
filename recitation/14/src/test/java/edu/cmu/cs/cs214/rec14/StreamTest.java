package edu.cmu.cs.cs214.rec14;

import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.cmu.cs.cs214.rec14.StreamPractice.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class StreamTest {
    private static final String DICT = "src/resources/wordlist.txt";

    @Test
    public void testFrequencyTable() {
        String[] words = {"to", "be", "or", "not", "to", "be"};
        assertEquals(frequencyTableRegular(Arrays.asList(words)), frequencyTableStream(Arrays.asList(words)));
    }

    @Test
    public void testSumAllSatisfying() {
        Integer[] nums = {-1, -2, -3, -42, 1, 2, 4, 6, 12};
        assertEquals(sumAllSatisfyingRegular(Arrays.asList(nums), a -> a > 0),
                sumAllSatisfyingStream(Arrays.asList(nums), a -> a > 0));
    }

    @Test
    public void testCharactersInString() {
        assertEquals(charactersInStringsRegular("foo", "bar"), charactersInStringsStream("foo", "bar"));
    }

    @Test
    public void testPalindromes() throws IOException {
        assertEquals(palindromesRegular(DICT), palindromesStream(DICT));
    }

    @Test
    public void testPalindromeCount() throws IOException {
        assertEquals(palindromeCountRegular(DICT), palindromeCountStream(DICT));
    }

    @Test
    public void testLongestPalindrome() throws IOException {
        assertEquals(longestPalindromeRegular(DICT), longestPalindromeStream(DICT));
    }

    @Test
    public void testFirstFibAbove() {
        assertEquals(BigInteger.valueOf(13), firstFibAbove(BigInteger.TEN));
        assertEquals(BigInteger.valueOf(10946), firstFibAbove(BigInteger.TEN.pow(4)));
    }

    @Test
    public void testIsFib() {
        assertTrue(isFib(BigInteger.ONE));
        assertTrue(isFib(BigInteger.valueOf(13)));
        assertFalse(isFib(BigInteger.TEN));
    }

    @Test
    public void testNthFib() {
        assertEquals(BigInteger.ONE, nthFib(1));
        assertEquals(BigInteger.ONE, nthFib(2));
        assertEquals(BigInteger.valueOf(267914296), nthFib(42));
    }
}
