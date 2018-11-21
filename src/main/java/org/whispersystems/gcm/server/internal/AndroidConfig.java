package org.whispersystems.gcm.server.internal;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AndroidConfig {

	@JsonProperty(value = "collapse_key")
	private String collapse_key;
	
	@JsonProperty(value = "priority")
	private String priority;
	
	@JsonProperty(value = "ttl")
	private String ttl;
	
	@JsonProperty(value = "data")
	private Map<String, String> data;

	@JsonProperty(value = "notification")
	private AndroidNotification notification;

	public AndroidConfig(String collapse_key, String priority, String ttl, Map<String, String> data,
			AndroidNotification notification) {
		super();
		this.collapse_key = collapse_key;
		this.priority = priority;
		this.ttl = ttl;
		this.data = data;
		this.notification = notification;
	}

	public String getCollapse_key() {
		return collapse_key;
	}

	public String getPriority() {
		return priority;
	}

	public String getTtl() {
		return ttl;
	}

	public Map<String, String> getData() {
		return data;
	}

	public AndroidNotification getNotification() {
		return notification;
	}
	


}
