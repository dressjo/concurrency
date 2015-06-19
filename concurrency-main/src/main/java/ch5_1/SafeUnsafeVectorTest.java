package ch5_1;

import static java.lang.System.out;

import java.util.Vector;

public class SafeUnsafeVectorTest {
	
	public static void main(String args[]) {
		
		 Vector<String> v = new Vector<String>();
		 v.add("String");
		 out.println("Size: " + v.size());		
		 out.println(v.get(0));
		 	 
		 out.println("Start concurrent modification");
		 	 
		 for(int i=0; i<10; i++) {
			 v.add("String");
			 new Thread(new UnsafeVectorRunnable(v, false)).start();
			 new Thread(new UnsafeVectorRunnable(v, true)).start();
		 }
		 
		
	}

}
