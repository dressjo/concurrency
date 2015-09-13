package ch6;

import java.util.Timer;
import java.util.TimerTask;

public class OutOfTime {

	public static void main(String[] args) throws Exception {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		Thread.sleep(10000);
		timer.schedule(new ThrowTask(), 1);
		Thread.sleep(5000);
		timer.cancel();
	}

	static class ThrowTask extends TimerTask {
		@Override
		public void run() {
			try {
				throw new RuntimeException();
			} catch (Throwable t) {
				System.out.println("Saved time thread");
				throw t;
			}
		}
	}
}