package com.technnocouple.dfcx.trainingphrase.generator.dao;

import java.util.List;

public class OpenAITextCompletionReq {
	
	String model;
	int max_tokens;
	double temperature;
	int top_p;
	int frequency_penalty;
	int presence_penalty;
	String prompt;
	List<String> stop;
	
	public OpenAITextCompletionReq() {}
	public OpenAITextCompletionReq(String model, int max_tokens, double temperature, int top_p, int frequency_penalty,
			int presence_penalty, String prompt, List<String> stop) {
		super();
		this.model = model;
		this.max_tokens = max_tokens;
		this.temperature = temperature;
		this.top_p = top_p;
		this.frequency_penalty = frequency_penalty;
		this.presence_penalty = presence_penalty;
		this.prompt = prompt;
		this.stop = stop;
	}
	public List<String> getStop() {
		return stop;
	}
	public void setStop(List<String> stop) {
		this.stop = stop;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getMax_tokens() {
		return max_tokens;
	}
	public void setMax_tokens(int max_tokens) {
		this.max_tokens = max_tokens;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getTop_p() {
		return top_p;
	}
	public void setTop_p(int top_p) {
		this.top_p = top_p;
	}
	public int getFrequency_penalty() {
		return frequency_penalty;
	}
	public void setFrequency_penalty(int frequency_penalty) {
		this.frequency_penalty = frequency_penalty;
	}
	public int getPresence_penalty() {
		return presence_penalty;
	}
	public void setPresence_penalty(int presence_penalty) {
		this.presence_penalty = presence_penalty;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	@Override
	public String toString() {
		return "OpenAITextCompletionReq [model=" + model + ", max_tokens=" + max_tokens + ", temperature=" + temperature
				+ ", top_p=" + top_p + ", frequency_penalty=" + frequency_penalty + ", presence_penalty="
				+ presence_penalty + ", prompt=" + prompt + ", stop=" + stop + "]";
	}

}
