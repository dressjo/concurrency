package ch5_5;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
	
	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
		
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
		for(int i = 0;  i< nThreads; i++) {
			Thread t = new Thread()  {
				
				@Override
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch(InterruptedException ie) {
						
						
					}
				}
				
			};
			t.start();
		}
		
		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return end - start;
		
	}
	
	public static void main(String args[]) {
		try {
			long time = new TestHarness().timeTasks(8, new Runnable() {
				@Override
				public void run() {
					System.out.println("Started running");			
				}
			});
			System.out.println(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
