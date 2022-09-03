package filecrawler;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
 
public class filecrawler {
	public static void main(String[] args) {
		System.out.println("Please enter a search word "); 
		Scanner input = new Scanner(System.in);
		String enteredWord = input.next();
		
		if (input != null) {
			input.close();
		} 
	
		String src = ("./");
		checkFiles(src, enteredWord.toLowerCase());
	}
	
	public static void checkFiles(String src, String check) {
		File[] listOfFiles = new File(src).listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	String filePath = (file.getAbsolutePath());
		    	readFile(filePath, check);
		    } else if (file.isDirectory()) {
		    	src = (file.getAbsolutePath());
		    	checkFiles(src, check);
		    } 
		}
	} 
	
	private static void readFile(String filePath, String check) {
		File file = new File(filePath);
		
		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNext()) {
				String data = reader.nextLine().toLowerCase();
			
				if(data.contains(check)) {
					System.out.println("Your word is available at: " + filePath);
				} 
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred at: " + filePath);
			e.printStackTrace();
		}  
	}
}