package ch5_1;

import java.util.Vector;

public class UnsafeVectorHelper {

	public static Object getLast(Vector<?> l) {		
			int index = l.size() - 1;
			return l.get(index);
	}
	
	public static void removeLast(Vector<?> l) {
			int index = l.size() - 1;
			l.remove(index);
	}

}
