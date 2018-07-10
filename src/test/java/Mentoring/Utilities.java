package Mentoring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
	
	
	public static int jobCount(String count) {
	    String temp="";
	    for(int i=0; i<count.length(); i++) {
	    	if(Character.isDigit(count.charAt(i))) {
	    		temp+=count.charAt(i);
	    	}
	    }
		return Integer.parseInt(temp);
	}
	
	public static List<String> loadTitleToArrayList() {
		List<String> jobTitle=new ArrayList();

		try {
		FileReader reader =new FileReader("jobTitle.txt");
		BufferedReader breader=new BufferedReader(reader);
		
		String temp=breader.readLine();// reads the first line of txt file
		while(temp!=null) {
			jobTitle.add(temp);    //adding string to arraylist
			temp=breader.readLine();  //reading a line from txt file
			
		}
		}catch(IOException e){
			System.out.println("Exception Caught");
		}
		return jobTitle;
	}

}
