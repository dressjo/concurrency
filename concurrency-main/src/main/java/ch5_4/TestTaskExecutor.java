package ch5_4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestTaskExecutor {
  public static void main(String args[]) {
	  
	  BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>();
	  Runnable producer = new Producer(taskQueue);
	  Consumer consumer = new Consumer(taskQueue);
	  Thread t1 = new Thread(producer);
	  t1.start();
	  Thread t2 = new Thread(consumer);
	  t2.start();
	  
	  try {
		Thread.sleep(1000 * 60 * 4);
		t1.interrupt();
		t2.interrupt();
	} catch (InterruptedException e) {
	
	}
	  
  }
	
}
