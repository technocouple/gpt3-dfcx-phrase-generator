package com.technnocouple.dfcx.trainingphrase.generator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.technnocouple.dfcx.trainingphrase.generator.dao.UserInput;
import com.technnocouple.dfcx.trainingphrase.generator.repository.DialogflowCXIntegration;
import com.technnocouple.dfcx.trainingphrase.generator.repository.OpenAIIntegration;

//TODO: PreRequisities:
//1.OpenAI account, API key
//2.Google Cloud account - Dialogflow CX virtualagent, GCP bearer token
//3.Please use log library for logging the flow as per your requirement.
//4.We can cache the API key, access_token as per your requirement
@RestController
public class TrainingPhraseGeneratorController {
	
	static String intentName = "";
	static String[] openAIPhrases = {};	
	private static Logger logger = LoggerFactory.getLogger(TrainingPhraseGeneratorController.class);
		
	/**
	 * this PostMapping method helps in fetching TrainingPhrases from OpenAI.
	 * @param messages
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin
	@PostMapping(path="/phrases")
	public String[] getTrainingPhrases(@RequestBody UserInput messages) throws Exception {
		logger.info("User entered intent : " + messages.getInput());
		intentName = messages.getInput();
		//call to OpenAI Completions API
		String[] entries = OpenAIIntegration.openAIPromptGenerator(messages.getInput()).split("\n");
		int noOfPhrase = entries.length;
		StringBuilder entryText = new StringBuilder();
		//Ensuring the Bullets and numbering are removed from the OpenAI response and add newline feed.
		for(int i=0;i<noOfPhrase; i++) {
			if(entries[i].startsWith("1") || entries[i].startsWith("2") || entries[i].startsWith("3") || entries[i].startsWith("4") ||
					entries[i].startsWith("5") || entries[i].startsWith("6") || entries[i].startsWith("7") || entries[i].startsWith("8") ||
					entries[i].startsWith("9") || entries[i].startsWith("10")) {
					entryText.append(entries[i].substring(3));
					entryText.append("\n");
			}else {
				entryText.append(entries[i]);
				entryText.append("\n");
				}
		}
		//finalize the phrases as per requirement
		openAIPhrases =  entryText.toString().replaceAll("\n\n", "").replaceAll("\"", "").split("\n");
		logger.info("Phrases : " + openAIPhrases);
		return openAIPhrases;
	}
	
	/**
	 * This PostMapping helps in training the Dialogflow CX virtual agent with the phrases generated for Intent
	 * @param messages
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin
	@PostMapping(path="/trainDfcx")
	public String trainDFCXVirtualAgent(@RequestBody UserInput messages) throws Exception {
		//please use log lib as required
		logger.info("User entered intent : " + messages.getInput());
		//System.out.println("DFCX Phrases  : " + openAIPhrases);
		DialogflowCXIntegration.createDfcxVirtualAgent(openAIPhrases,intentName);
		return "Sucessfully Updated into your Dialogflow CX Console!";
	}
	
	
	
	
}
