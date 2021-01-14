package com.transcriber.vta;

import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.Encoder;

public class VideoToAudioConverter {

	private AudioAttributes audioAttributes;
	private EncodingAttributes encodingAttributes;
	
	public VideoToAudioConverter() {
		// Declare Audio Attributes object and set properties of audio desired to be converted.
		AudioAttributes audioAttributes = new AudioAttributes();
		audioAttributes.setCodec("libmp3lame");
		// here 64kbit/s is 64000
		audioAttributes.setBitRate(128000);
		audioAttributes.setChannels(2);
		audioAttributes.setSamplingRate(44100);
		this.setAudioAttributes(audioAttributes);
		
		// Declare Encoding Attributes object and set properties of enoding desired of the converted video.
		EncodingAttributes encodingAttributes = new EncodingAttributes();
		encodingAttributes.setFormat("mp3");
		encodingAttributes.setAudioAttributes(this.getAudioAttributes());
		this.setEncodingAttributes(encodingAttributes);
	}
	
	public String videoToAudio(String inputFilename) {
		// Replacing name with mp3 ending
		String[] words = inputFilename.split("\\.");
		words[words.length-1] = ".mp3";
		String outputFilename = "./src/main/resources/Audio/output/" + String.join("", words) + ".mp3";
		
		MultimediaObject source = new MultimediaObject(getVideoFromResources(inputFilename));
		File target = new File(outputFilename);
		
		try {
			System.out.println("Encoding " + inputFilename + " to audio");
			  Encoder encoder = new Encoder();  
			  encoder.encode(source, target, encodingAttributes);
			} catch (Exception e) {  
			   /*Handle here the video failure*/   
			   e.printStackTrace();
			   return null;
			}
		return outputFilename;
		
	}
	
	private File getVideoFromResources(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource("Video/"+filename).getFile());
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
