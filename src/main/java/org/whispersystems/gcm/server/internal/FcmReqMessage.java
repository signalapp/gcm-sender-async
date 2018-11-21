package org.whispersystems.gcm.server.internal;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#Notification
 * @author user
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FcmReqMessage {

	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "data")
	private Map<String, String> data;
	
	@JsonProperty(value = "notification")
	private Notification notification;
	
	@JsonProperty(value = "android")
	private AndroidConfig android;

	@JsonProperty(value = "apns")
	private ApnsConfig apns;
	
	@JsonProperty(value = "token")
	private String token;
	
	@JsonProperty(value = "topic")
	private String topic;
	
	@JsonProperty(value = "condition")
	private String condition;
	
	

	public FcmReqMessage() {
		super();
	}

	public FcmReqMessage(String name, Map<String, String> data, Notification notification, AndroidConfig android,
			String token, String topic, String condition) {
		super();
		this.name = name;
		this.data = data;
		this.notification = notification;
		this.android = android;
		this.token = token;
		this.topic = topic;
		this.condition = condition;
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getData() {
		return data;
	}

	public Notification getNotification() {
		return notification;
	}

	public AndroidConfig getAndroid() {
		return android;
	}

	public String getToken() {
		return token;
	}

	public String getTopic() {
		return topic;
	}

	public String getCondition() {
		return condition;
	}

	public ApnsConfig getApns() {
		return apns;
	}


}
