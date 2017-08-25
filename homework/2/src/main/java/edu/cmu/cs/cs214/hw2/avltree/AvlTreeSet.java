package edu.cmu.cs.cs214.hw2.avltree;

/**
 * An AvlTreeSet is a balanced binary search tree that stores a set of ints.
 *
 * Each node in the AvlTreeSet stores its height explicitly in the node, and the
 * tree is balanced by maintaining the property that each node's children differ
 * in height by at most 1.
 */
public class AvlTreeSet {
    private Node mRoot;

    /**
     * Constructs an empty AvlTreeSet.
     */
    public AvlTreeSet() {
        mRoot = new Node();
    }

    /**
     * Returns the number of nodes in this AvlTreeSet.
     *
     * @return the number of nodes in this AvlTreeSet
     */
    public int size() {
        return mRoot.size();
    }

    /**
     * Inserts an integer into the AvlTreeSet and re-balances the tree. If the
     * integer is already a node in the tree, this method has no effect.
     *
     * @param value The integer to insert.
     */
    public void insert(int value) {
        mRoot = mRoot.insert(value);
    }

    /**
     * Returns <code>true</code> if this AvlTreeSet has no nodes.
     *
     * @return <code>true</code> if this AvlTreeSet has no nodes.
     */
    public boolean isEmpty() {
        return mRoot.isEmpty();
    }

    /**
     * Removes an integer from the AvlTreeSet.
     *
     * As a precondition the integer to remove must be stored in the AvlTreeSet.
     *
     * @throws IllegalStateException
     *             If this AvlTreeSet does not contain the integer.
     * @param value
     *            The integer to remove.
     */
    public void remove(int value) {
        mRoot = mRoot.remove(value);
    }

    /**
     * Returns <code>true</code> if this AvlTreeSet contains the specified
     * integer. This method will return false if the integer is not in the tree or if the
     * tree is empty.
     *
     * @param value
     *            The integer whose presence in this AvlTreeSet is to be tested.
     * @return <code>true</code> if this AvlTreeSet contains the specified
     *         integer.
     */
    public boolean contains(int value) {
        return mRoot.contains(value);
    }

    /**
     * Returns the maximum value stored in the AvlTreeSet. For example, for a
     * tree that contains the nodes 1, 5, and 10, this method returns 10.
     *
     * As a precondition this method requires the AvlTreeSet to be non-empty.
     *
     * @throws IllegalStateException
     *             If this AvlTreeSet is empty.
     * @return The maximum value stored in the AvlTreeSet.
     */
    public int getMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Tried to get maximum item of an empty AvlTreeSet");
        }
        return mRoot.getMax();
    }

    /**
     * Returns the minimum value stored in the AvlTreeSet. For example, for a
     * tree that contains the nodes 1, 5, and 10, this method returns 1.
     *
     * As a precondition this method requires the AvlTreeSet to be non-empty.
     *
     * @throws IllegalStateException
     *             If this AvlTreeSet is empty.
     * @return The minimum value stored in the AvlTreeSet.
     */
    public int getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Tried to get minimum item of an empty AvlTreeSet");
        }
        return mRoot.getMin();
    }

    /**
     * Returns the height of the AvlTreeSet.
     *
     * The height of a tree is defined to be the maximum path length from the
     * root to a leaf in the tree, with the height of an empty tree being -1.
     *
     * @return The height of the AvlTreeSet.
     */
    public int getHeight() {
        return mRoot.getHeight();
    }

    /**
     * basic node for the AvlTree structure
     * @author alvin
     *
     */
    private static class Node {
        // @ invariant Math.abs(left.height - right.height) <= 1

        private int mValue;
        private int mHeight;
        private Node mLeft;
        private Node mRight;
        private int mSize;

        Node() {
            mValue = -1;
            mHeight = -1;
            mLeft = this;
            mRight = this;
            mSize = 0;
        }

        Node(int value, Node left, Node right) {
            mValue = value;
            mLeft = left;
            mRight = right;
            mHeight = Math.max(left.getHeight(), right.getHeight()) + 1;
            mSize = left.size() + right.size();
        }

        public int size() {
            return mSize;
        }

        public boolean isEmpty() {
            return mSize == 0;
        }

        public Node getLeft() {
            return mLeft;
        }

        public Node getRight() {
            return mRight;
        }

        public int getHeight() {
            return mHeight;
        }

        public int getValue() {
            return mValue;
        }

        public Node insert(int value) {
            if (isEmpty()) {
                return new Node(value, this, this);
            }

            if (value <= mValue) {
                return new Node(mValue, mLeft.insert(value), mRight).balance();
            }

            if (value > mValue) {
                return new Node(mValue, mLeft, mRight.insert(value)).balance();
            }

            return this;
        }

        public Node remove(int value) {
            if (isEmpty()) {
                throw new IllegalStateException("Tried to remove an item not in the AvlTreeSet");
            }

            if (value == mValue) {
                if (mLeft.isEmpty()) {
                    return mRight.balance();
                }
                if (mRight.isEmpty()) {
                    return mLeft.balance();
                }
                final int leftMax = mLeft.getMax();
                return new Node(leftMax, mLeft.remove(leftMax), mRight).balance();
            }

            if (value < mValue) {
                return new Node(mValue, mLeft.remove(value), mRight).balance();
            } else {
                return new Node(mValue, mLeft, mRight.remove(value)).balance();
            }
        }

        public boolean contains(int value) {
            if (isEmpty()) {
                return false;
            }

            if (value == mValue) {
                return true;
            }

            if (value < mValue) {
                return mLeft.contains(value);
            }

            return false;
        }

        public int getMax() {
            if (mRight.isEmpty()) {
                return mValue;
            }
            return mRight.getMax();
        }

        public int getMin() {
            if (mLeft.isEmpty()) {
                return mValue;
            }
            return mLeft.getMin();
        }

        private Node balance() {
            final int leftHeight = mLeft.getHeight();
            final int rightHeight = mRight.getHeight();

            if (leftHeight > rightHeight + 1) {
                // Left subtree is too tall.
                if (mLeft.getLeft().getHeight() > mLeft.getRight().getHeight()) {
                    // Outer left grandchild is too tall.
                    return singleRotateRight();
                } else {
                    // Else inner left grandchild is too tall.
                    return doubleRotateRight();
                }
            }

            if (rightHeight > leftHeight + 1) {
                // Right subtree is too tall.
                if (mRight.getRight().getHeight() > mRight.getLeft().getHeight()) {
                    // Outer right grandchild is too tall.
                    return singleRotateLeft();
                } else {
                    // Else inner left grandchild is too tall.
                    return doubleRotateLeft();
                }
            }

            // Already balanced.
            return this;
        }

        private Node singleRotateRight() {
            int value = mLeft.getValue();
            Node left = mLeft.getLeft();
            Node right = new Node(mValue, mLeft.getRight(), mRight);
            return new Node(value, left, right);
        }

        private Node doubleRotateRight() {
            int value = mLeft.getRight().getValue();
            Node left = new Node(mLeft.getValue(), mLeft.getLeft(), mLeft.getRight().getLeft());
            Node right = new Node(mValue, mLeft.getRight().getRight(), mRight);
            return new Node(value, left, right);
        }

        private Node singleRotateLeft() {
            int value = mRight.getValue();
            Node left = new Node(mValue, mLeft, mRight.getLeft());
            Node right = mRight.getRight();
            return new Node(value, left, right);
        }

        private Node doubleRotateLeft() {
            int value = mRight.getLeft().getValue();
            Node left = new Node(mValue, mLeft, mRight.getLeft().getLeft());
            Node right = new Node(mRight.getValue(), mRight.getLeft().getRight(), mRight.getRight());
            return new Node(value, left, right);
        }

    }
}
