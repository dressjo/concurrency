package ch5_1;

import java.util.Vector;

public class SafeVectorHelper {

	public static Object getLast(Vector<?> l) {
		synchronized (l) {
			int index = l.size() - 1;
			return l.get(index);
		}
	}

	public static void removeLast(Vector<?> l) {
		synchronized (l) {
			int index = l.size() - 1;
			l.remove(index);
		}
	}

}
