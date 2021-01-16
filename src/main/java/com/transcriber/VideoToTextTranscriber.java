package com.transcriber;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.transcriber.explorer.FileExplorer;
import com.transcriber.stt.SpeechToTextSystem;
import com.transcriber.vta.VideoToAudioConverter;

import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

@SpringBootApplication
public class VideoToTextTranscriber {

	public static void main(String[] args) throws IllegalArgumentException, InputFormatException, EncoderException, InterruptedException, IOException{
		SpringApplication.run(VideoToTextTranscriber.class, args);

		FileExplorer fileExplorer = new FileExplorer();
		//String startDir = "C:\\Users\\JairAxelLizarraga\\Box\\Box Jair\\Planeación de donaciones\\Talleres\\Open P-TECH\\Recursos generados\\Tema 4 - Introducción a la programación con Node-RED";
        String startDir = "C:\\Users\\JairAxelLizarraga\\Desktop\\Nueva carpeta";
		List<String> videos = fileExplorer.listVideosFromFolder(startDir); 
		
		SpeechToTextSystem stts = new SpeechToTextSystem();
		VideoToAudioConverter videoToAudioConverter = new VideoToAudioConverter();
		
		List<String> audios = new ArrayList<String>();
		for(String inputFilename: videos) {
			String audioPath = videoToAudioConverter.videoToAudio(inputFilename);
			audios.add(audioPath);
			stts.getTextFromAudio(audioPath);
		}	

		FileWriter writer = new FileWriter(startDir + "\\audios.txt"); 
		for(String str: audios) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
		System.out.println("Finished. List:"+ startDir + "\\audios.txt");
	}
	

}
