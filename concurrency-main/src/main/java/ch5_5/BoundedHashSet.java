package ch5_5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {
	
	private final Set<T> set;
	private final Semaphore sem;
	
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.sem = new Semaphore(bound);
	}
	
	public boolean add(T object) throws InterruptedException{
		boolean wasAdded = false;
		try {
			sem.acquire();
			wasAdded = set.add(object);
			return wasAdded;
		} finally {
			if(!wasAdded) {
				sem.release();
			}
		}
	}
	
	public boolean remove(T object) {
		boolean wasRemoved = false;
		if(wasRemoved = set.remove(object)) {
		   sem.release();
		}
		return wasRemoved;
	}
	
	public static void main(String args[]) {

		final BoundedHashSet<String> bs = new BoundedHashSet<String>(11);

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					Random random = new Random();
					
					long sleep = (long) Math.abs(random.nextFloat() * 50) + 10;
					System.out.println("Will sleep: " + sleep);
					try {
						String stringToBeAdded = this.toString();
						System.out.println("Adding " + bs.add(stringToBeAdded));
						Thread.sleep(sleep * 1000);
						System.out.println("Removing " + bs.remove(stringToBeAdded));
					} catch (InterruptedException e) {
						// do nothing
					}
				}
			}).start();
		}
		
		
	}
	

}
