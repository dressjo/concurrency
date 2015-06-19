package ch5_1;

import home.jd.common.data.BasicData;

public class CollectionCloning {
	public static void main(String args[]) {
		for(String name : BasicData.generateSimpleDataList()) {
			 System.out.println(name);
		 }
	}
}
