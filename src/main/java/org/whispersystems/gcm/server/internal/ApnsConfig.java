package org.whispersystems.gcm.server.internal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApnsConfig {

	@JsonProperty(value = "headers")
	private String headers;
	
	@JsonProperty(value = "payload")
	private ApnsPayload payload;

	public ApnsConfig(String headers, ApnsPayload payload) {
		super();
		this.headers = headers;
		this.payload = payload;
	}

	public String getHeaders() {
		return headers;
	}

	public ApnsPayload getPayload() {
		return payload;
	}
	

}
