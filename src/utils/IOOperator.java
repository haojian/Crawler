package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.io.FileOutputStream;
import java.io.InputStream;

public class IOOperator {
	
	public static void main(String[] args) {
		File dir = IOOperator.createDir("UTF8");
		transformFiles("total", "UTF8");
	}
	
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
	
	public static void writeToFileUTF8(String filename, String content, boolean isappend){
		BufferedWriter out;
		try {
			 out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, isappend), "UTF-8"));
			 out.write(content);
			 out.close();
		} catch (Exception e) {
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
	
	
	public static void ConvertFiletoUTF8(String srcpath, String outpath){
		System.out.println(Charset.defaultCharset());
		String charset = "GBK";
		
		
	}
	
	
	public static void transformFiles(String inputDir, String outputDir){
		File dir = new File(inputDir);
		File[] lists = dir.listFiles();
		for(File tmp : lists){
			File target = new File(outputDir + "/" + tmp.getName());
			ConvertFiletoUTF8(tmp, "GBK", target, "UTF8");			
		}
	}
	
	public static void ConvertFiletoUTF8(File source, String srcEncoding, File target, String tgtEncoding) {
	    BufferedReader br = null;
	    BufferedWriter bw = null;
	    try{
	        br = new BufferedReader(new InputStreamReader(new FileInputStream(source), "GBK"));
	        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), tgtEncoding));
	        char[] buffer = new char[16384];
	        int read;                               
	        while ((read = br.read(buffer)) != -1)
	            bw.write(buffer, 0, read);
	        br.close();
	        bw.close();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
}
