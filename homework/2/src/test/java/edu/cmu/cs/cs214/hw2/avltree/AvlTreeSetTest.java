package edu.cmu.cs.cs214.hw2.avltree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AvlTreeSetTest {
    private AvlTreeSet mTestTree;

    /** Called before each test case method. */
    @Before
    public void setUp() throws Exception {
        // Start each test case method with a brand new AvlTreeSet object.
        mTestTree = new AvlTreeSet();
    }

    /** Called after each test case method. */
    @After
    public void tearDown() throws Exception {
        // Don't need to do anything here.
    }

    /** Tests that an empty tree has size 0. */
    @Test
    public void testEmptyTreeSize() {
        // First argument is the expected value.
        // Second argument is the actual returned value.
        assertEquals(0, mTestTree.size());
    }

    // TODO: Add more test case methods below!

}
