import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 */

public class Utilities {
	public static void sendFile(String path, OutputStream os) {
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
//			e.printStackTrace();
		}

	}
	
	public static long getFileSize(String path) {
		File f = new File(path);
		return f.length();
	}
}
