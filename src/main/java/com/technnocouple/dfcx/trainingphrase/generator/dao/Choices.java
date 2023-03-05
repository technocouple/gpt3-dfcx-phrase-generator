package com.technnocouple.dfcx.trainingphrase.generator.dao;

public class Choices {
	
	private String text;
	private int index;
	private String finish_reason;
	public Choices() {}
	public Choices(String text, int index, String finish_reason) {
		super();
		this.text = text;
		this.index = index;
		this.finish_reason = finish_reason;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getFinish_reason() {
		return finish_reason;
	}
	public void setFinish_reason(String finish_reason) {
		this.finish_reason = finish_reason;
	}
	@Override
	public String toString() {
		return "Choices [text=" + text + ", index=" + index + ", finish_reason=" + finish_reason + "]";
	}
	
	

}
