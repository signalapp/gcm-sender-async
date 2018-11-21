package org.whispersystems.gcm.server.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://developer.apple.com/library/archive/documentation/NetworkingInternet/Conceptual/RemoteNotificationsPG/PayloadKeyReference.html#//apple_ref/doc/uid/TP40008194-CH17-SW1
 * @author user
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApnsPayload {

	@JsonProperty(value = "alert")
	private ApnsAlert alert;
	
	@JsonProperty(value = "badge")
	private String badge;
	
	@JsonProperty(value = "sound")
	private String sound;
	
	@JsonProperty(value = "content-available")
	private String contentAvailable;
	
	@JsonProperty(value = "category")
	private String category;
	
	@JsonProperty(value = "thread-id")
	private String threadId;

	public ApnsPayload(ApnsAlert alert, String badge, String sound, String contentAvailable, String category,
			String threadId) {
		super();
		this.alert = alert;
		this.badge = badge;
		this.sound = sound;
		this.contentAvailable = contentAvailable;
		this.category = category;
		this.threadId = threadId;
	}

	public ApnsAlert getAlert() {
		return alert;
	}

	public String getBadge() {
		return badge;
	}

	public String getSound() {
		return sound;
	}

	public String getContentAvailable() {
		return contentAvailable;
	}

	public String getCategory() {
		return category;
	}

	public String getThreadId() {
		return threadId;
	}
	
	
}
