package ch5_1;

import static java.lang.System.out;

import java.util.Vector;

public class UnsafeVectorRunnable implements Runnable {

	private Vector<String> v;

	private boolean remove;

	public UnsafeVectorRunnable(Vector<String> v, boolean remove) {
		this.v = v;
		this.remove = remove;
	}

	public void run() {
		out.println("Running thread");
		if (!remove) {
			UnsafeVectorHelper.getLast(v);
		} else {
			UnsafeVectorHelper.removeLast(v);
		}
	}

}
