package utils;

import java.util.ArrayList;
import java.util.Stack;

public class URLManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	}
	
}
