package ch5_1;

import static java.lang.System.out;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTester {
	
	public static void main(String[] args) {
	    
		final Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();
		map.put("KEY1", 10);
		map.put("KEY2", 20);
		map.put("KEY3", 30);
		map.put("KEY4", 40);
		System.out.println(map.size());
		
		new Thread(new Runnable() {
			public void run() {
				for(Map.Entry<String, Integer> entry : map.entrySet())	 {
					out.println(entry.toString());
				}
			}
			
		}).start();
		
		out.println(map.containsKey("KEY1"));
	}

}
