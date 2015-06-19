package ch3_1;

public class AtomicRunnable implements Runnable {

	private AtomicObject test;

	public AtomicRunnable(AtomicObject test) {
		this.test = test;
	}

	public void run() {
		for (int i = 0; i < 100000; i++) {
			// using AtomicLong instead
			//synchronized (test) {
				test.hits.incrementAndGet();
			//}
			System.out.println(test.hits);
		}
	}

}
