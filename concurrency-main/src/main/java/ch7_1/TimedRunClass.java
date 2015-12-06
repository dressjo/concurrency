package ch7_1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import util.ExcptionUtil;

public class TimedRunClass {

	private static ExecutorService taskExecutor = Executors
			.newFixedThreadPool(1);

	public static void timedRun(Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {

		Future<?> task = taskExecutor.submit(r);

		try {
			Object x = task.get(timeout, unit);
			System.out.println(x);
		} catch (TimeoutException e) {
			// task will be cancelled below
			System.out.println("Aborted by TimeoutException");
		} catch (ExecutionException e) {
			// exception thrown in task; rethrow
			throw ExcptionUtil.launderThrowable(e.getCause());
		} finally {
			// Harmless if task already completed
			task.cancel(true); // interrupt if running
		}
		
		
	}
	
	public static void main(String[] args) { 
		
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(7000l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		try {
			TimedRunClass.timedRun(task, 8000l, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) { 			
			System.out.println("Exectuion was interrupted");
		}
		
	}
	
	
}
