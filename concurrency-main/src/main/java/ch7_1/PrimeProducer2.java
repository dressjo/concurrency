package ch7_1;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PrimeProducer2 extends Thread {
	
   private final BlockingQueue<BigInteger> queue;
   private final long startTime = System.nanoTime();

   public PrimeProducer2(BlockingQueue<BigInteger> queue) {
      this.queue = queue;
   }
		
   @Override
   public void run() {
		
      try {
		 BigInteger p = BigInteger.ONE;
		 while (!Thread.currentThread().isInterrupted()) {
			Thread.sleep(1000l);
		    queue.put(p = p.nextProbablePrime());
	     }
	  } catch (InterruptedException consumed) {
		/* Allow thread to exit */
	  }
   }
		
   public void cancel() { 
      interrupt(); 
   } 
   
   public boolean needMorePrimes() { 
	   long estimatedTime = System.nanoTime() - startTime;
	   if(TimeUnit.NANOSECONDS.toSeconds(estimatedTime) > 3) {
		   return false;
	   }
	   return true;
   }
   
	public static void main(String args[]) throws InterruptedException {
		LinkedBlockingQueue<BigInteger> primes = new LinkedBlockingQueue<BigInteger>();
		PrimeProducer2 producer = new PrimeProducer2(primes);
		producer.start();
		try {
			while (producer.needMorePrimes())
				System.out.println(primes.take());
		} catch(InterruptedException ie) { 
			System.out.println("Rethrowing interruped exception");
			throw ie;
		} finally {
			producer.cancel();
		}

	}
   
}
	
