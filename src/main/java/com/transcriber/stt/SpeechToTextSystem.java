package com.transcriber.stt;


import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SpeechToTextSystem {
	
	SpeechToText speechToText;
	
	public SpeechToTextSystem() {
		IamAuthenticator authenticator = new IamAuthenticator("1zEYYJ-lutML595EvXAMqI9foaRNUev9X6fTeH9M8pmT");
		speechToText = new SpeechToText(authenticator);
		speechToText.setServiceUrl("https://api.us-south.speech-to-text.watson.cloud.ibm.com");
	}
	
	public String getTextFromAudio(String filename) throws IOException {
        File audio = getAudioFromResources("audio2.wav");
		return doTextRecognition(audio, "es-MX_BroadbandModel");
	}
	
	public File getAudioFromResources(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource("Audio/"+filename).getFile());
	}
	
	public String doTextRecognition(File audio, String model) throws FileNotFoundException {
	    String text = "";
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
		}
		return text;
	}

}
