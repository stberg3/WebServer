import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class MyHandler implements HttpHandler {
	private void sendFile(String path, OutputStream os) {
		System.out.println(String.format("Parsing path %s", path));
	
		try {
			System.out.println("Reading the file " + path);
			FileInputStream in = new FileInputStream(path);
	         
			byte c;
			while ((c = (byte) in.read()) != -1) {
				os.write(c);
			}
			
			in.close();
		} catch (IOException e) {
			System.out.println(String.format("Error reading file %s", path));
		}

	}
	
	private long getFileSize(String path) {
		File f = new File(path);
		return f.length();
	}
	
	private void setHeaders(HttpExchange t, String path) {
		if(path.endsWith("/") || path.endsWith("html")) {
			t.getResponseHeaders().set("Content-Type", "text/html");
			t.getResponseHeaders().add("Content-Type", "charset=utf-8");
		} else if (path.endsWith("js")) {
			t.getResponseHeaders().set("Content-Type", "application/javascript");
			t.getResponseHeaders().add("Content-Type", "charset=utf-8");
		} else if (path.endsWith("css")) {
			t.getResponseHeaders().set("Content-Type", "text/css");
			t.getResponseHeaders().add("Content-Type", "charset=utf-8");
		} else if (path.endsWith("woff") || path.contains("woff")) {
			t.getResponseHeaders().set("Content-Type", "application/font-woff");
		} else if (path.endsWith("jpg") || path.endsWith("jpeg")) {
			t.getResponseHeaders().add("Content-Type", "image/jpeg");
		}
	}
	
    @Override
    public void handle(HttpExchange t) throws IOException {
    	String p = t.getRequestURI().toString();
    	String path;
    	
		if(p.equals("/")) {
			path = "/home/sam/Desktop/eclipse-workspace/WebServer/static/index.html";
		} else {
			path = "/home/sam/Desktop/eclipse-workspace/WebServer/static" + t.getRequestURI().toString();
		}

		setHeaders(t, path);
        t.sendResponseHeaders(200, getFileSize(path));

        System.out.println("--------------------REQUEST--------------------");
        System.out.println(String.format("URI:\t%s", t.getRequestURI()));
        System.out.println(String.format("METHOD:\t%s", t.getRequestMethod()));
        System.out.println(String.format("PATH:\t%s", t.getHttpContext().getPath()));
        System.out.println(String.format("MOD_PATH:\t%s", path));
        
        
        System.out.println("--------------------RESPONSE--------------------");
        System.out.println("HEADERS: ");
        for(String key : t.getResponseHeaders().keySet()) {
        	System.out.println(String.format("\t%s", key));
        	for(String val : t.getResponseHeaders().get(key)) {
        		System.out.println(String.format("\t\t%s", val));
        	}
        }
                       
        OutputStream os = t.getResponseBody();
        sendFile(path, os);
        os.close();
    }
}

