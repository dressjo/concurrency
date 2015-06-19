package home.jd.common.data;

import java.util.Arrays;
import java.util.List;

public class BasicData {
	
	private static String[] simpleData = new String[] {"Adam", "Alice", "Bob", "John", "Will"};
	
	public static List<String> generateSimpleDataList() {
		return Arrays.asList(simpleData);
	}

}
