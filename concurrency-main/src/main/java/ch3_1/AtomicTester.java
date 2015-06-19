package ch3_1;

public class AtomicTester {

	public static void main(String[] args) {

		AtomicObject testObject = new AtomicObject();
		for (int i = 0; i < 20; i++) {
			new Thread(new AtomicRunnable(testObject)).start();
		}
	}

}
