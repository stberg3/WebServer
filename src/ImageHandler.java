import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ImageHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpEx) throws IOException {
		String path = "static/"+ httpEx.getRequestURI().getPath();
		
		httpEx.getResponseHeaders().set("Content-Type", "image/jpeg");
		httpEx.sendResponseHeaders(200, Utilities.getFileSize(path));

        OutputStream os = httpEx.getResponseBody();
        Utilities.sendFile(path, os);
        os.close();
	}

}
