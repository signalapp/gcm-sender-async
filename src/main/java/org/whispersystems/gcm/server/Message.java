/**
 * Copyright (C) 2015 Open Whisper Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.whispersystems.gcm.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.whispersystems.gcm.server.Message.Builder;
import org.whispersystems.gcm.server.internal.FcmRequestEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Message {

  protected static final ObjectMapper objectMapper = new ObjectMapper();

  
	

	public Message(){
		
	};
	

  public abstract String serialize() throws JsonProcessingException ;

  /**
   * Construct a new Message using a Builder.
   * @return A new Builder.
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {

    private String              collapseKey     = null;
    private Long                ttl             = null;
    private Boolean             delayWhileIdle  = null;
    private Map<String, String> data            = null;
    private List<String>        registrationIds = new LinkedList<>();
    private String              priority        = null;
	private String 				token = null;
	private String 				condition = null;
	private String 				title = null;
	private String 				body = null;
	private String 				android_channel_id = null;
	private String 				icon = null;
	private String 				sound = null;
	private String 				tag = null;
	private String 				click_action = null;
	private String 				color = null;
	private String 				body_loc_key = null;
	private String 				title_loc_key = null;
	private List<String> 		body_loc_args = new LinkedList<>();
	private List<String> 		title_loc_args = new LinkedList<>();
	private boolean             isFcm = false;
    
    private Builder() {}

    /**
     * @param collapseKey The GCM collapse key to use (optional).
     * @return The Builder.
     */
    public Builder withCollapseKey(String collapseKey) {
      this.collapseKey = collapseKey;
      return this;
    }

    /**
     * @param seconds The TTL (in seconds) for this message (optional).
     * @return The Builder.
     */
    public Builder withTtl(long seconds) {
      this.ttl = seconds;
      return this;
    }

    /**
     * @param delayWhileIdle Set GCM delay_while_idle (optional).
     * @return The Builder.
     */
    public Builder withDelayWhileIdle(boolean delayWhileIdle) {
      this.delayWhileIdle = delayWhileIdle;
      return this;
    }

    /**
     * Set a key in the GCM JSON payload delivered to the application (optional).
     * @param key The key to set.
     * @param value The value to set.
     * @return The Builder.
     */
    public Builder withDataPart(String key, String value) {
      if (data == null) {
        data = new HashMap<>();
      }
      data.put(key, value);
      return this;
    }

    /**
     * Set the destination GCM registration ID (mandatory).
     * @param registrationId The destination GCM registration ID.
     * @return The Builder.
     */
    public Builder withDestination(String registrationId) {
      this.registrationIds.clear();
      this.registrationIds.add(registrationId);
      return this;
    }

    /**
     * Set the GCM message priority (optional).
     *
     * @param priority Valid values are "normal" and "high."
     *                 On iOS, these correspond to APNs priority 5 and 10.
     * @return The Builder.
     */
    public Builder withPriority(String priority) {
      this.priority = priority;
      return this;
    }
    
	public Builder withTitle(String string) {
		this.title = string;
		return this;
	}

	public Builder withBody(String string) {
		this.body = string;
		return this;
	}
	
	public Builder flagFcm() {
		this.isFcm = true;
		return this;
	}
	
	public Builder withToken(String token) {
		this.token = token;
		return this;
	}
    /**
     * Construct a message object.
     *
     * @return An immutable message object, as configured by this builder.
     */
    public Message build() {
      if (registrationIds.isEmpty() && this.token.isEmpty()) {
        throw new IllegalArgumentException("You must specify a destination!");
      }
      if(this.isFcm) {
    	  return new FcmMessage(token,null,title, body, null,null,null,null, null,null,null,null,null,null);
      }
      return new GcmMessage( collapseKey, ttl, delayWhileIdle,
    		  data, registrationIds,
    		  priority);
    }

	

	
  }


}
