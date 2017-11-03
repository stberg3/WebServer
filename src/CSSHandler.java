import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CSSHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpEx) throws IOException {
		String path = "static/"+ httpEx.getRequestURI().getPath();
		
		if(path.contains("woff")) {
			httpEx.getResponseHeaders().set("Content-Type", "application/font-woff");
		} else {
			httpEx.getResponseHeaders().set("Content-Type", "text/css");
			httpEx.getResponseHeaders().add("Content-Type", "charset=utf-8");
		}
		
		httpEx.sendResponseHeaders(200, Utilities.getFileSize(path));

        OutputStream os = httpEx.getResponseBody();
        Utilities.sendFile(path, os);
        os.close();
	}

}
