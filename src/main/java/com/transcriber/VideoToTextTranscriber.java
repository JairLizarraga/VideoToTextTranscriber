package com.transcriber;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.transcriber.stt.SpeechToTextSystem;
import com.transcriber.vta.VideoToAudioConverter;

@SpringBootApplication
public class VideoToTextTranscriber {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(VideoToTextTranscriber.class, args);
		
		// Get files from folder
		
		// Convert video to audio
		String inputFilename = "source.mp4";
		VideoToAudioConverter videoToAudioConverter = new VideoToAudioConverter();
		String audioPath = videoToAudioConverter.videoToAudio(inputFilename);
		System.out.println("Finished on: " + audioPath);

		
		//words[words.length-1] = "mp3";
		
		// Convert EVERY video to audio
		
		// get text from every video
		
		// Save text to file with same name
		
		//SpeechToTextSystem stts = new SpeechToTextSystem();
		//System.out.println(stts.getTextFromAudio("audio2"));
	}

}
