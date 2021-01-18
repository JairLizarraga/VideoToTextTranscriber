package com.transcriber;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.transcriber.App.App;

import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

@SpringBootApplication
public class VideoToTextTranscriber {

	public static void main(String[] args) throws IllegalArgumentException, InputFormatException, EncoderException, InterruptedException, IOException{
		SpringApplication.run(VideoToTextTranscriber.class, args);

		App app = new App();
		app.run();
	}
	

}
