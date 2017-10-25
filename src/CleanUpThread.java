import com.sun.net.httpserver.HttpServer;

public class CleanUpThread extends Thread {
	private HttpServer server;
	
	public CleanUpThread(HttpServer server) {
		this.server = server;
	}
	
	public void run() {
		System.err.println("Gracefully shutting down the server...");
		server.stop(2);
    }
}