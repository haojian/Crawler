package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

public class URLManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URLManager.getInstance();
	}
	
	public static URLManager singleton;
	
	public static URLManager getInstance(){
		if(singleton == null)
			singleton = new URLManager();
		return singleton;
	}

	public ArrayList<String> URLList;
	public Stack<String> ProcessStack;
	
	
	
	
	public URLManager(){
		URLList = new ArrayList<String>();
		ProcessStack = new Stack<String>();
		loadFile("./urllists_1.txt");
		System.out.println(URLList.size());
		for(String tmp : URLList){
			System.out.println(tmp);
		}
	}
	
	public void loadFile(String path){
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = "";
			while((line = br.readLine()) != null){
				if(line.isEmpty() || line.startsWith("//")){
					continue;
				}
				URLList.add(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
