package ch7_1;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class PrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;

	PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted())
				queue.put(p = p.nextProbablePrime());
		} catch (InterruptedException consumed) {
			/* Allow thread to exit */
			System.out.println("I was interrupted");
			return;
		}
		System.out.println("Interrupeted normally");
	}
	
	public synchronized BigInteger[] get() {
		return queue.toArray(new BigInteger[queue.size()]);
	}

	public void cancel() {
		interrupt();
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		PrimeProducer primeProducer = new PrimeProducer(new LinkedBlockingQueue<BigInteger>(10));
		primeProducer.start();
		Thread.sleep(10000l);
		primeProducer.cancel();
		for(int i=0; i < primeProducer.get().length; i++) {
			System.out.println(primeProducer.get()[i]);
		}
		
	}
}

class Consume implements Runnable { 
	
	@Override
	public void run() { 
	
	}
}
