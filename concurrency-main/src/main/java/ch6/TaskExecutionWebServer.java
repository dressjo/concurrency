package ch6;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class TaskExecutionWebServer {
	private static final int NTHREADS = 10;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while (true) {
			final Socket connection = socket.accept();
			
			Runnable task = new Runnable() {
				@Override
				public void run() {
					handleRequest(connection);
				}
			};
			exec.execute(task);
		}
	}
	
	private static void handleRequest(final Socket connection) { 
		 try {
			 
			 BufferedInputStream buf = new BufferedInputStream(connection.getInputStream());
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			 
			 byte[] buffer = new byte[1024];
			 int len = 0;
			 while((len = buf.read(buffer)) != -1) {
				 out.write(buffer, 0, len);
			 }
			 System.out.println(out.toString());
		
			connection.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
}