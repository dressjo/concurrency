package ch6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import util.Request;

class LifecycleWebServer {

	private final ExecutorService exec = Executors.newFixedThreadPool(10);
	private ServerSocket socket;

	public void start() throws IOException {
		socket = new ServerSocket(80);
		while (!exec.isShutdown()) {
			try {
				final Socket conn = socket.accept();
				exec.execute(new Runnable() {
					@Override
					public void run() {
						handleRequest(conn);
					}
				});
			} catch (RejectedExecutionException e) {
				if (!exec.isShutdown())
					System.out.println("task submission rejected");
				e.printStackTrace();
			} catch(SocketException se) {
				se.getMessage();
			}
		}
	}

	public void stop() {
		exec.shutdown();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Server socket close()");
		}
	}

	private void handleRequest(Socket connection) {
		try {
			Request req = readRequest(connection);
			if (isShutdownRequest(req))
				stop();
			else
				dispatchRequest(req);
		} catch (IOException e) {
             e.printStackTrace();
		}
	}

	private Request readRequest(Socket connection) throws IOException {
		return new Request(connection);
	}

	private boolean isShutdownRequest(Request request) {
		return request.getRequestText().equals("quit");
	}

	private void dispatchRequest(Request request) {
		System.out.println("Received request: " + request.getRequestText());
	}

	public static void main(String args[]) {
					
			Runnable startNewServer = new Runnable() {
				
				@Override
				public void run() {
					LifecycleWebServer server = new LifecycleWebServer();
					try {
						server.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			new Thread(startNewServer).start();
						
			new SocketClient().connectAndSend("Test");
			new SocketClient().connectAndSend("Test");
			new SocketClient().connectAndSend("quit");
		

	}

}