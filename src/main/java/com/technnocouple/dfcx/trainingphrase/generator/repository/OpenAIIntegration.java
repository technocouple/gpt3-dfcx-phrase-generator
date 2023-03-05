package com.technnocouple.dfcx.trainingphrase.generator.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.technnocouple.dfcx.trainingphrase.generator.dao.OpenAITextCompletion;
import com.technnocouple.dfcx.trainingphrase.generator.dao.OpenAITextCompletionReq;

public class OpenAIIntegration {
	private static Logger logger = LoggerFactory.getLogger(OpenAIIntegration.class);
	/**
	 * This method connects to OpenAI completions API and return the Phrases
	 * @param intent
	 * @return
	 */
	public static String openAIPromptGenerator(String intent) {
		//OpenAI GPT3 URL : <base_url> + <completions_api>
		String url = "https://api.openai.com/v1/completions";
		//add Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.ALL);
		headers.setAccept(list);
		//TODO: get this from https://beta.openai.com/account/api-keys 90days validity for free account
		headers.setBearerAuth("<OpenAI_API_KEY>");
		
		//Build Request object for OpenAI Completion API
		OpenAITextCompletionReq mRequest = new OpenAITextCompletionReq();
		//set Model as TEXT_DAVINCI
		mRequest.setModel("text-davinci-003");
		//PromptEngineering - prompt to generate training phrases for a given Intent
		mRequest.setPrompt("Generate 10 professional user utterances for the intent "
				+ "\""+ intent +"\" and display without numbering and bullet points");
		//Temperature helps to create phrases in creative to realistic way based on the configured value
		mRequest.setTemperature(0.2);
		mRequest.setMax_tokens(450);
		mRequest.setTop_p(1);
		mRequest.setFrequency_penalty(0);
		mRequest.setPresence_penalty(0);
		
		HttpEntity<OpenAITextCompletionReq> waRequest = new HttpEntity<OpenAITextCompletionReq>(mRequest, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<OpenAITextCompletion> response = restTemplate.exchange(url, HttpMethod.POST, waRequest, OpenAITextCompletion.class);
		logger.info("GenertativeAI Api Response : " + response.getBody().getChoices()[0].getText());
		return response.getBody().getChoices()[0].getText();
	}

}
