package com.technnocouple.dfcx.trainingphrase.generator.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.technnocouple.dfcx.trainingphrase.generator.dao.CreateIntentRequest;
import com.technnocouple.dfcx.trainingphrase.generator.dao.DFCXApiResponse;
import com.technnocouple.dfcx.trainingphrase.generator.dao.Parts;
import com.technnocouple.dfcx.trainingphrase.generator.dao.TrainingPhrases;

public class DialogflowCXIntegration {
	
	private static Logger logger = LoggerFactory.getLogger(DialogflowCXIntegration.class);
	
	/**
     * This method helps in invoking Google Dialogflow CX api's to 
     * 1. Create Intent and upload Training Phrases
     * 2. Train the Virtual Agent
     * @param trainingPhrases
     */
    public static void createDfcxVirtualAgent(String[] trainingPhrases, String intentName) {
    	int numOfPhrase = trainingPhrases.length;
    	String entryText = "";
    	logger.info("NumberOfPhrases : " + numOfPhrase);
    	
    	//URL of Dialogflow CX :
    	//TODO:Change this as per your url : POST https://REGION_ID-dialogflow.googleapis.com/v3/projects/PROJECT_ID/locations/REGION_ID/agents/AGENT_ID/intents
    	String url = "https://global-dialogflow.googleapis.com/v3/projects/<<PROJECT_ID>>/locations/"
    			+ "<<REGION_ID>>/agents/<<AGENT_ID>>/intents";
    	//add headers
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		List<MediaType> list = new ArrayList<MediaType>();
	
		list.add(MediaType.ALL);
		//TODO: Change this based on GCP project : <PROJECT_ID>
		headers.add("x-goog-user-project", "<PROJECT_ID>");
		headers.setAccept(list);
		//Bearer Token
		//TODO: to get temporary GCP token run this command from Google cloud shell "gcloud auth print-access-token"
		//this can also be retrieved through proper Google cloud api and store it in cache as per your requirement
		headers.setBearerAuth("<<GCP_BEARER_TOKEN>>");
		//Request object creation
		CreateIntentRequest createIntentRequest = new CreateIntentRequest();
		List<TrainingPhrases> tPhrases = new ArrayList<>();
		
		//Ensuring the Bullets and numberings are removed from the OpenAI response and add newline feed.
		for(int i=0;i<numOfPhrase; i++) {
			int j =0;
			if(trainingPhrases[i].startsWith("1") || trainingPhrases[i].startsWith("2") || trainingPhrases[i].startsWith("3") || 
					trainingPhrases[i].startsWith("4") || trainingPhrases[i].startsWith("5") || trainingPhrases[i].startsWith("6") 
					|| trainingPhrases[i].startsWith("7") || trainingPhrases[i].startsWith("8") ||
					trainingPhrases[i].startsWith("9") || trainingPhrases[i].startsWith("10")) {
					entryText = trainingPhrases[i].substring(3);
					logger.info("text " + entryText);
			}else {
				entryText = trainingPhrases[i];
				}
			logger.info("Cleaned TrainingPhrases " + entryText);
			
			//validation and forming request object in the required format
			/*
			 * { "displayName": "TravelDispName", "trainingPhrases": [ { "parts": [ {
			 * "parameterId": "", "text": "I need to plan my trip" } ], "repeatCount": 1 },
			 * { "parts": [ { "parameterId": "", "text":
			 * "Help me to build itinerary for the upcoming trip" } ], "repeatCount": 1 } ]
			 * }
			 */
			if(entryText !=null && !entryText.isEmpty()) {
				List<Parts> partsList1 = new ArrayList<>();
				Parts firstText = new Parts();
				firstText.setParameterId("");
				firstText.setText(entryText);
				partsList1.add(0, firstText);
				
				TrainingPhrases trPh1 = new TrainingPhrases();
				trPh1.setParts(partsList1);
				trPh1.setRepeatCount("1");
				
				tPhrases.add(j, trPh1);
				j++;
				}
			else {

				List<Parts> partsList1 = new ArrayList<>();
				Parts firstText = new Parts();
				firstText.setParameterId("");
				firstText.setText(entryText);
				partsList1.add(0, firstText);
				
				TrainingPhrases trPh1 = new TrainingPhrases();
				trPh1.setParts(partsList1);
				trPh1.setRepeatCount("1");
				
				tPhrases.add(j, trPh1);
				j++;
				
			}
		}
		
		createIntentRequest.setDisplayName(intentName);
		createIntentRequest.setTrainingPhrases(tPhrases);
		
		logger.info("CreateIntentRequest Object: " + createIntentRequest);
		
		HttpEntity<CreateIntentRequest> waRequest = new HttpEntity<CreateIntentRequest>(createIntentRequest, headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange(url, HttpMethod.POST, waRequest, DFCXApiResponse.class);
	
	}

}
