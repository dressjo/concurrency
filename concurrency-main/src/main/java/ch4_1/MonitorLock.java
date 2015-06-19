package ch4_1;

import net.jcip.annotations.GuardedBy;

public class MonitorLock {
	
	private Object monitorObject = new Object();
	@GuardedBy("monitorObject") Object widget;
	
	void someMethod() { 
		synchronized(monitorObject) {
			
		}
	}

	
}
