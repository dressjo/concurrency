package util;

public class ExcptionUtil {
	
	public static RuntimeException launderThrowable(Throwable t) {
		if(t instanceof RuntimeException) {
			throw (RuntimeException)t;
		} else if(t instanceof Error) {
			throw (Error)t;
		} else {
		   t.getCause().printStackTrace();
		   throw new IllegalArgumentException("Illegal argument");
		}
	}

}
