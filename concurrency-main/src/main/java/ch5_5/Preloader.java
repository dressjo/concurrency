package ch5_5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {
	
	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
		
		@Override
		public ProductInfo call() throws DataLoadException {
			try {
				System.out.println("sleeping");
				Thread.sleep(10L * 1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return new ProductInfo("test");
		}
	});
	
	
	private final Thread thread = new Thread(future);
	
	public void start() {
		thread.start();
	}
	
	public ProductInfo get() throws DataLoadException, InterruptedException {
		try {
			return future.get();
		} catch(ExecutionException ex) {
			Throwable t = ex.getCause();
			if(t instanceof DataLoadException) {
				throw (DataLoadException)t;
			} else {
				throw launderException(t);
			}
		}
	}
	
	public static RuntimeException launderException(Throwable t) {
		if(t instanceof RuntimeException) {
			throw (RuntimeException)t;
		} else if(t instanceof Error) {
			throw (Error)t;
		} else {
		   throw new IllegalArgumentException("Illegal argument");
		}
	}
	
	public class DataLoadException extends Exception {
	
		private static final long serialVersionUID = 1L;

		public DataLoadException() {
			super("Failed to load data");
		}
	}
	
	public static void main(String[] args) {
		Preloader preloader = new Preloader();
		preloader.start();
		try {
			System.out.println(preloader.get().getName());
		} catch (DataLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
