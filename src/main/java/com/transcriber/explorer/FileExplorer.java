package com.transcriber.explorer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExplorer {

	public FileExplorer() {
	}
	
    public List<String> listVideosFromFolder(String startDir){
    	List<String> result = new ArrayList<>();
    	
        try (Stream<Path> walk = Files.walk(Paths.get(startDir))) {
            result = walk.filter(pathname -> isVideoFilter(pathname))
                    .map(x -> x.toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean isVideoFilter(Path pathname) {
		String[] words = pathname.getFileName().toString().split("\\.");
		return words[words.length-1].equals("mp4");
    }
	
}
