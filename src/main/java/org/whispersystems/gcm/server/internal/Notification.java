package org.whispersystems.gcm.server.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {

	
	@JsonProperty(value = "title")
	private String title;
	
	@JsonProperty(value = "body")
	private String body;
	
	

	public Notification() {
		super();
	}

	public Notification(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}
	
	
}
