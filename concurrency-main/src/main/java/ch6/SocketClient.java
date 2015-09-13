package ch6;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

	public void connectAndSend(String sendString) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 80);
			OutputStream outs = socket.getOutputStream();
			outs.write(sendString.getBytes());
			outs.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
	    	 e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]) {
		SocketClient client = new SocketClient();
		for (int i = 0; i < 20; i++) {
			client.connectAndSend("HelloWorld_" + (i+1));
		}
	}

}
