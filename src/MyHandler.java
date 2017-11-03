import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class MyHandler implements HttpHandler {	
    @Override
    public void handle(HttpExchange t) throws IOException {
//    	String p = t.getRequestURI().toString();
//    	String path = "/home/sam/Desktop/eclipse-workspace/WebServer/static/index.html";
    	String path = "static/index.html";

    	t.getResponseHeaders().set("Content-Type", "text/html");
		t.getResponseHeaders().add("Content-Type", "charset=utf-8");
		
		t.sendResponseHeaders(200, Utilities.getFileSize(path));

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
        Utilities.sendFile(path, os);
        os.close();
    }
}

