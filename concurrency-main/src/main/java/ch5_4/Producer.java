package ch5_4;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	
	boolean running = true;
	
	private final BlockingQueue<Task> taskQueue;
	
	public Producer(BlockingQueue<Task> taskQueue) {
	   this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while(running) {
			try {
				Thread.currentThread().sleep(3000L);
				taskQueue.add(new Task());
			} catch (InterruptedException e) {
			    running = false;
			}
		}
		
	}
}

