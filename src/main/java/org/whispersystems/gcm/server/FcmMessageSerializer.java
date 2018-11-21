package org.whispersystems.gcm.server;

import org.whispersystems.gcm.server.internal.FcmRequestEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

public class FcmMessageSerializer extends Message {
	
	private final  FcmRequestEntity requestEntity;

	public FcmMessageSerializer(FcmRequestEntity requestEntity) {
		super();
		this.requestEntity = requestEntity;
	}

	public String serialize() throws JsonProcessingException {
	    return objectMapper.writeValueAsString(requestEntity);
	}
	
}
