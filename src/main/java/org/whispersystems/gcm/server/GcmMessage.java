package org.whispersystems.gcm.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.whispersystems.gcm.server.internal.GcmRequestEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

public class GcmMessage extends Message {
	
	  private final String              collapseKey;
	  private final Long                ttl;
	  private final Boolean             delayWhileIdle;
	  private final Map<String, String> data;
	  private final List<String>        registrationIds;
	  private final String              priority;

	public GcmMessage(String collapseKey, Long ttl, Boolean delayWhileIdle, Map<String, String> data,
			List<String> registrationIds, String priority) {
		
		this.collapseKey     = collapseKey;
	    this.ttl             = ttl;
	    this.delayWhileIdle  = delayWhileIdle;
	    this.data            = data;
	    this.registrationIds = registrationIds;
	    this.priority        = priority;
		
	}
	

	public String serialize() throws JsonProcessingException {
		GcmRequestEntity requestEntity = new GcmRequestEntity( collapseKey, ttl, delayWhileIdle,
				  data, registrationIds,
				  priority);

		  String string = objectMapper.writeValueAsString(requestEntity);
	    return string;
	  }
	
}
