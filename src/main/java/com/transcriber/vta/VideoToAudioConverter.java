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
		audioAttributes.setBitRate(256000);
		audioAttributes.setChannels(1);
		audioAttributes.setSamplingRate(16000);
		this.setAudioAttributes(audioAttributes);
		
		// Declare Encoding Attributes object and set properties of encoding desired of the converted video.
		EncodingAttributes encodingAttributes = new EncodingAttributes();
		encodingAttributes.setFormat("mp3");
		encodingAttributes.setAudioAttributes(this.getAudioAttributes());
		this.setEncodingAttributes(encodingAttributes);
	}


	public String videoToAudio(String inputFilename) throws IllegalArgumentException, InputFormatException, EncoderException {
		
		String[] words = inputFilename.split("\\.");
		words[words.length-1] = ".mp3";
		String outputFilename = String.join("", words);

		File inputFile = new File(inputFilename);
		MultimediaObject source = new MultimediaObject(inputFile);
		File target = new File(outputFilename);
		
		System.out.print("Converting " + inputFilename + " to audio... ");
		Encoder encoder = new Encoder();  
		System.out.println(encoder.getAudioEncoders().toString());
		encoder.encode(source, target, encodingAttributes);
		System.out.println("Done");
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
