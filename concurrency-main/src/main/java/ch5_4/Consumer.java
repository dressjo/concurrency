package ch5_4;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	
	boolean running = true;
	
	private final BlockingQueue<Task> taskQueue;
	
	public Consumer(BlockingQueue<Task> taskQueue) {
	   this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while(running) {
			try {
				taskQueue.take().executeTask();
			} catch (InterruptedException e) {
				
			}
	}		
	}
}

