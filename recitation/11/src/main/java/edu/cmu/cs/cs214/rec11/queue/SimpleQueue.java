package edu.cmu.cs.cs214.rec11.queue;

public interface SimpleQueue<E> {

    /**
     * Returns true iff queue is empty.
     */
    boolean isEmpty();

    /**
     * Returns the current size of the queue.
     */
    int size();

    /**
     * Returns the first element of the queue without removing it from the queue.
     */
    E peek();

    /**
     * Appends the data to the back of the queue.
     */
    void enqueue(E element);

    /**
     * Removes and returns the element from the front of the queue.
     */
    E dequeue() throws InterruptedException;
}
