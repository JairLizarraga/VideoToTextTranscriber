package com.transcriber;

import java.io.IOException;
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

	public static void main(String[] args) throws IOException, IllegalArgumentException, InputFormatException, EncoderException {
		SpringApplication.run(VideoToTextTranscriber.class, args);

		
		// Get the videos path from root folder
		FileExplorer fileExplorer = new FileExplorer();
		//String startDir = "C:\\Users\\JairAxelLizarraga\\Box\\Box Jair\\Planeación de donaciones\\Talleres\\Open P-TECH\\Recursos generados\\Tema 4 - Introducción a la programación con Node-RED";
        String startDir = "C:\\Users\\JairAxelLizarraga\\Desktop\\Nueva carpeta";
		List<String> videos = fileExplorer.listVideosFromFolder(startDir); 
		
		// Convert video to audio
		// Get text from audio
		SpeechToTextSystem stts = new SpeechToTextSystem();
		
		for(String inputFilename: videos) {
			VideoToAudioConverter videoToAudioConverter = new VideoToAudioConverter();
			String audioPath = videoToAudioConverter.videoToAudio(inputFilename);
			System.out.println("Texto reconocido: ");
			System.out.println(stts.getTextFromAudio(audioPath));
			System.out.println();
			
		}
		
		
		// Save text to file with same name
		System.out.println("End");
	}

}
