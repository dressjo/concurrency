package util;

import java.io.IOException;
import java.net.Socket;

public class Request {
	
	private final String requestText;
		
	public Request(Socket connection) throws IOException { 
		this.requestText = CIOUtil.parseRequest(connection);		
	}	
	
	public String getRequestText() {
		return requestText;
	}
	
}
