package edu.cmu.cs.cs214.rec11.queue.runnable;

import edu.cmu.cs.cs214.rec11.queue.SimpleQueue;

public class Consumer implements Runnable {

	private SimpleQueue<Integer> q;
	private int n;

	public Consumer(SimpleQueue<Integer> q, int n) {
		this.q = q;
		this.n = n;
	}


	@Override
	public void run() {
		while(n > 0) {
			try {
				q.dequeue();
			} catch (InterruptedException e) {
				throw new AssertionError(e);
			}
			n--;
		}
	}
}
