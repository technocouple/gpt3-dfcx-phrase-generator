package com.technnocouple.dfcx.trainingphrase.generator.dao;

import java.util.Arrays;

public class DFCXApiResponse {
	
	String messaging_product;
	ContactRes[] contacts; 
	Messages[] messages;
	public DFCXApiResponse() {}
	public DFCXApiResponse(String messaging_product, ContactRes[] contacts, Messages[] messages) {
		super();
		this.messaging_product = messaging_product;
		this.contacts = contacts;
		this.messages = messages;
	}
	public String getMessaging_product() {
		return messaging_product;
	}
	public void setMessaging_product(String messaging_product) {
		this.messaging_product = messaging_product;
	}
	public ContactRes[] getContacts() {
		return contacts;
	}
	public void setContacts(ContactRes[] contacts) {
		this.contacts = contacts;
	}
	public Messages[] getMessages() {
		return messages;
	}
	public void setMessages(Messages[] messages) {
		this.messages = messages;
	}
	@Override
	public String toString() {
		return "DFCXApiResponse [messaging_product=" + messaging_product + ", contacts=" + Arrays.toString(contacts)
				+ ", messages=" + Arrays.toString(messages) + "]";
	}
	
	

}
