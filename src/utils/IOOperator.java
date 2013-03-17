package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.InputStream;

public class IOOperator {
	public static void writeToFile(String filename, String content, boolean isappend){
		FileWriter writer;
		try {
			writer = new FileWriter(filename, isappend);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(String dirName, String filename, String content, boolean isappend){
		FileWriter writer;
		try {
			File dir = new File(dirName);
			dir.mkdir();
			File file = new File(dir, "filename");
			
			writer = new FileWriter(file);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static File createDir(String dirStr){
		File tmp = new File(dirStr);
		if(tmp.exists() && tmp.isDirectory())
			return tmp;
		else if(!tmp.exists())	{
			tmp.mkdir();
			return tmp;
		}else
			return null;
	}
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}
}
