package edu.cmu.cs.cs214.rec11.queue.runnable;

import edu.cmu.cs.cs214.rec11.queue.SimpleQueue;

public class SpinWaitConsumer implements Runnable {

	private SimpleQueue<Integer> q;
	private int n;

	public SpinWaitConsumer(SimpleQueue<Integer> q, int n) {
		this.q = q;
		this.n = n;
	}

	/**
	 * Waits a while (1 million loops) for the queue to be non-empty. 
	 */
	private boolean spinWait(SimpleQueue<Integer> q) {
		for (int i = 0; i < 1000000; ++i) {
			if (!q.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void run() {
		while(spinWait(q) && n > 0) {
			try {
				q.dequeue();
			} catch (InterruptedException e) {
				throw new AssertionError(e);
			}
			n--;
		}
	}
}
