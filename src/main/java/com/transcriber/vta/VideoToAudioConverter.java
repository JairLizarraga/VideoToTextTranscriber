package com.transcriber.vta;

import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;

public class VideoToAudioConverter {

	private AudioAttributes audioAttributes;
	private EncodingAttributes encodingAttributes;
	
	public VideoToAudioConverter() {
		// Declare Audio Attributes object and set properties of audio desired to be converted.
		AudioAttributes audioAttributes = new AudioAttributes();
		audioAttributes.setCodec("libmp3lame");
		audioAttributes.setBitRate(128000);
		audioAttributes.setChannels(2);
		audioAttributes.setSamplingRate(44100);
		this.setAudioAttributes(audioAttributes);
		
		// Declare Encoding Attributes object and set properties of encoding desired of the converted video.
		EncodingAttributes encodingAttributes = new EncodingAttributes();
		encodingAttributes.setFormat("wav");
		encodingAttributes.setAudioAttributes(this.getAudioAttributes());
		this.setEncodingAttributes(encodingAttributes);
	}


	public String videoToAudio(String inputFilename) throws IllegalArgumentException, InputFormatException, EncoderException {
		File inputFile = new File(inputFilename);
		
		String[] words = inputFile.getName().split("\\.");
		words[words.length-1] = ".wav";
		String outputFilename = "src/main/resources/Audio/output/" + String.join("", words);
		
		MultimediaObject source = new MultimediaObject(inputFile);
		File target = new File(outputFilename);
		
		System.out.println("Transforming " + inputFilename + " to audio");
		Encoder encoder = new Encoder();  
		encoder.encode(source, target, encodingAttributes);
		System.out.println("Transformed to audio. Output file: " + outputFilename);
		return outputFilename;
		
	}
	
	
	public String videoToAudio2(String inputFilename) {
		// Replacing name with mp3 ending
		String[] words = inputFilename.split("\\.");
		words[words.length-1] = ".wav";
		String outputFilename = "./src/main/resources/Audio/output/" + String.join("", words);
		

        ClassLoader classLoader = getClass().getClassLoader();
        File video = new File(classLoader.getResource("Video/"+inputFilename).getFile());
        
		MultimediaObject source = new MultimediaObject(video);
		File target = new File(outputFilename);
		
		try {
			System.out.println("Transforming " + inputFilename + " to audio");
			  Encoder encoder = new Encoder();  
			  encoder.encode(source, target, encodingAttributes);
			} catch (Exception e) {  
			   /*Handle here the video failure*/   
			   e.printStackTrace();
			   return null;
			}
		System.out.println("Transformed to audio. Output file: " + outputFilename);
		return outputFilename;
		
	}
	

	public AudioAttributes getAudioAttributes() {
		return audioAttributes;
	}

	public void setAudioAttributes(AudioAttributes audioAttributes) {
		this.audioAttributes = audioAttributes;
	}

	public EncodingAttributes getEncodingAttributes() {
		return encodingAttributes;
	}

	public void setEncodingAttributes(EncodingAttributes encodingAttributes) {
		this.encodingAttributes = encodingAttributes;
	}
	
}
