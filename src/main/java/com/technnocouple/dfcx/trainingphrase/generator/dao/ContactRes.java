package com.technnocouple.dfcx.trainingphrase.generator.dao;

public class ContactRes {
	
	String input;
	String wa_id;
	public ContactRes() {}
	public ContactRes(String input, String wa_id) {
		super();
		this.input = input;
		this.wa_id = wa_id;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getWa_id() {
		return wa_id;
	}
	public void setWa_id(String wa_id) {
		this.wa_id = wa_id;
	}
	@Override
	public String toString() {
		return "ContactRes [input=" + input + ", wa_id=" + wa_id + "]";
	}
	
	

}
