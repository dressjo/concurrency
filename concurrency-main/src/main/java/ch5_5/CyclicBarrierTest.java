package ch5_5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierTest {
	
	final int N;
	final float[][] data;
	final CyclicBarrier barrier;
	final Semaphore done = new Semaphore(1);
	final AtomicInteger times = new AtomicInteger(0);

	class Worker implements Runnable {
		int myRow;

		Worker(int row) {
			myRow = row;
		}

		@Override
		public void run() {
			while (!done()) {
				// processRow(myRow);
				try {
					System.out.println("Processing row");
					Thread.sleep(3000l);
					barrier.await();
				} catch (InterruptedException ex) {
					return;
				} catch (BrokenBarrierException ex) {
					return;
				}
			}
		}
	}

	public CyclicBarrierTest(float[][] matrix) {
		data = matrix;
		N = matrix.length;
		try {
			done.acquire();
		} catch (InterruptedException e) {
			System.out.println("Should not happen");
		}
		System.out.println("Available permits" + done.availablePermits());
		barrier = new CyclicBarrier(N, new Runnable() {
			@Override
			public void run() {
				// mergeRows(...);				
				times.incrementAndGet();
				System.out.println("Mergin rows + " + times.intValue());
				if (times.intValue() == 5) {
					done.release();
				}

			}
		});
		for (int i = 0; i < N; ++i)
			new Thread(new Worker(i)).start();

		waitUntilDone();

	}

	private boolean done() {
		return done.availablePermits() == 1;
	}

	private boolean waitUntilDone() {
		while (done.availablePermits() == 0) {
		}
		return true;
	}
	
	public static void main(String args[]) {
		float [] [] matrix = new float [4][3];
		System.out.println(matrix.length);
		new CyclicBarrierTest(matrix);
	}

}