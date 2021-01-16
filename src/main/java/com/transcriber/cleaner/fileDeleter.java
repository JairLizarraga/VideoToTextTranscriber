package com.transcriber.cleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileDeleter {

	public static void main(String[] args) throws IOException {
	    File file = new File("C:\\Users\\JairAxelLizarraga\\Desktop\\Nueva carpeta\\audios.txt");
	    
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	 
	    String line;
	    while((line = br.readLine()) != null){
	    	Files.deleteIfExists(Paths.get(line));
	    	System.out.println("Deleted " + line);
	    }
	    
	    br.close();
	    fr.close();
	    System.out.println("Finished");
	}

}
