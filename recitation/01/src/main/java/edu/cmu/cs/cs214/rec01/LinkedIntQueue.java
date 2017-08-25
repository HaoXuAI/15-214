package edu.cmu.cs.cs214.rec01;

import edu.cmu.cs.cs214.rec01.IntQueue;

/**
 * A resizable-array implementation of the {@link IntQueue} interface. The head of
 * the queue starts out at the head of the array, allowing the queue to grow and
 * shrink in constant time.
 * 
 * NOTE TO STAFF: This class has been renamed "LinkedIntQueue" to emphasize the
 * polymorphism aspect of the assignment. That is, that they are two separate
 * implementations which implement the same {@link IntQueue} interface. It is
 * almost identical to the {@link ArrayIntQueue} class (the only difference is that
 * the {@link ArrayIntQueue} has a couple of bugs which the students must fix).
 * 
 * DO NOT DISTRIBUTE THIS SOURCE CODE TO STUDENTS!
 * 
 * @author Alex Lockwood
 */
public class LinkedIntQueue implements IntQueue {

    /**
     * An array holding this queue's data
     */
    private int elementData[];

    /**
     * Index of the next dequeue-able value
     */
    private int head;

    /**
     * Current size of queue
     */
    private int size;

    /**
     * The initial size for new {@link IntQueue} instances
     */
    private static final int INITIAL_SIZE = 10;

    /**
     * Constructs an empty queue with an initial capacity of ten
     */
    public LinkedIntQueue() {
        elementData = new int[INITIAL_SIZE];
        head = 0;
        size = 0;
    }
    
    /** {@inheritDoc} */
    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = 0;
        }
        size = 0;
        head = 0;
    }

    /** {@inheritDoc} */
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Integer value = elementData[head];
        head = (head + 1) % elementData.length;
        size--;
        return value;
    }

    /** {@inheritDoc} */
    public boolean enqueue(Integer value) {
        ensureCapacity();
        int tail = (head + size) % elementData.length;
        elementData[tail] = value;
        size++;
        return true;
    }

    /** {@inheritDoc} */
    public boolean isEmpty() {
        return size == 0;
    }

    /** {@inheritDoc} */
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData[head];
    }

    /** {@inheritDoc} */
    public int size() {
        return size;
    }

    /**
     * Increases the capacity of this <tt>Queue</tt> instance, if necessary, to
     * ensure that it can hold at least size + 1 elements.
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = 2 * oldCapacity + 1;
            int[] newData = new int[newCapacity];
            for (int i = head; i < oldCapacity; i++) {
                newData[i - head] = elementData[i];
            }
            for (int i = 0; i < head; i++) {
                newData[oldCapacity - head + i] = elementData[i];
            }
            elementData = newData;
            head = 0;
        }
    }
}