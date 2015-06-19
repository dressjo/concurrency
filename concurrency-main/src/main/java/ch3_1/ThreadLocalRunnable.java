package ch3_1;

import static java.lang.System.out;

public class ThreadLocalRunnable implements Runnable {
	
	private String name;
	
	public ThreadLocalRunnable(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 1; i < 10; i++) {
			out.println(this.name + " - " + ThreadLocalResource.getConnection().toString());
		}

	}

}
