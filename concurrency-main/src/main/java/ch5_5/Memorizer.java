package ch5_5;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memorizer<A,V> implements Computable<A, V> {
	
	private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private final Computable<A,V> c;
	
	public Memorizer(Computable<A,V> c) { 
		this.c = c;
	}
	
	@Override
	public V compute(final A arg) {
		while (true) {
			Future<V> f = cache.get(arg);
			if (f == null) {
				Callable<V> callable = new Callable<V>() {
					@Override
					public V call() throws Exception {
						return c.compute(arg);
					}
				};
				
				FutureTask<V> ft = new FutureTask<V>(callable);
				f = cache.putIfAbsent(arg, ft);
				if (f == null) { 
					f = ft; 
					ft.run(); 
				}
				
				try {
					return f.get();
				} catch (InterruptedException e) {
					cache.remove(arg);
				} catch (ExecutionException e) {
					throw launderException(e);
				}

			}

		}

	}
	
	public static RuntimeException launderException(Throwable t) { 
		if(t instanceof RuntimeException) {
			throw (RuntimeException)t;
		} else if(t instanceof Error) {
			throw (Error)t;
		} else {
		   t.getCause().printStackTrace();
		   throw new IllegalArgumentException("Illegal argument");
		}
	}
	
	public static void main(String[] args)  {
		
	}
	
}
