import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class WebServer {
	public static void main(String[] args) {
		HttpServer server = null;
		try {
			// backlog: 	"maximum number of incoming TCP connections 
			//				 which the system will queue internally"
			
			int backlog = 0; // 0 means set to default value
			
			server = HttpServer.create(new InetSocketAddress(2007), backlog);
		} catch (IOException e) {
			System.err.println("Error creating server");
			e.printStackTrace();
		}
		
		// if the server doesn't exist at this point, there's nothing else to do
		if(server == null) return;
		
		server.createContext("/", new MyHandler());
		server.createContext("/index.html", new MyHandler());
		server.createContext("/images", new ImageHandler());
		server.createContext("/css", new CSSHandler());
		server.createContext("/js", new JSHandler());
		server.createContext("/downloads", new DLHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
		Runtime.getRuntime().addShutdownHook(new CleanUpThread(server));
	}

}
