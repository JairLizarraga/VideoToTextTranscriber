package com.transcriber.cleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileDeleter {

	public static void main(String[] args) throws IOException {
		String startDir = "C:\\Users\\JairAxelLizarraga\\Box\\Box Jair\\Planeación de donaciones\\Talleres\\Open P-TECH\\Recursos generados\\Tema 4 - Introducción a la programación con Node-RED\\audios.txt";
	    File file = new File(startDir);
	    
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	 
	    String line;
	    while((line = br.readLine()) != null){
	    	System.out.println(line);
	    	Files.deleteIfExists(Paths.get(line));
	    	System.out.println("Deleted " + line);
	    }
	    
	    br.close();
	    fr.close();
	    System.out.println("Finished");
	}

}
