package com.technnocouple.dfcx.trainingphrase.generator.dao;

import java.util.Arrays;

public class OpenAITextCompletion {
	
	private String id;
	private String object;
	private Choices[] choices;
	public OpenAITextCompletion() {}
	public OpenAITextCompletion(String id, String object, Choices[] choices) {
		super();
		this.id = id;
		this.object = object;
		this.choices = choices;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public Choices[] getChoices() {
		return choices;
	}
	public void setChoices(Choices[] choices) {
		this.choices = choices;
	}
	@Override
	public String toString() {
		return "OpenAITextCompletion [id=" + id + ", object=" + object + ", choices=" + Arrays.toString(choices) + "]";
	}

	
}
