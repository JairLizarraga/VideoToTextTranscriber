package com.transcriber.stt;


import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SpeechToTextSystem {
	
	SpeechToText speechToText;
	
	public SpeechToTextSystem() {
		IamAuthenticator authenticator = new IamAuthenticator("1zEYYJ-lutML595EvXAMqI9foaRNUev9X6fTeH9M8pmT");
		speechToText = new SpeechToText(authenticator);
		speechToText.setServiceUrl("https://api.us-south.speech-to-text.watson.cloud.ibm.com");
	}
	
	public void getTextFromAudio(String filePath) throws IOException {
        File audio = new File(filePath);
        String model = "es-MX_BroadbandModel";
	    String text = "";

		String[] words = filePath.split("\\.");
		words[words.length-1] = ".txt";
		String outputFilename = String.join("", words);
		
		RecognizeOptions options = new RecognizeOptions.Builder()
				.audio(audio)
	            .contentType(HttpMediaType.AUDIO_WAV) //select your format
	            .model(model)
	            .build();

		SpeechRecognitionResults response = speechToText.recognize(options).execute().getResult();
		
		if(!response.getResults().isEmpty()) {
			List<SpeechRecognitionResult> transcripts = response.getResults();
			for(SpeechRecognitionResult res: transcripts) {
				if(res.isXFinal()) {
					text += res.getAlternatives().get(0).getTranscript();
					break;
				}
			}

			File outputFile = new File(outputFilename);
			outputFile.createNewFile();
			Files.write(Paths.get(outputFilename), text.getBytes());
		}
		
		System.out.println("Text created on: " + outputFilename);
	}

}
