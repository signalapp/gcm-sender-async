package org.whispersystems.gcm.server.internal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://developer.apple.com/library/archive/documentation/NetworkingInternet/Conceptual/RemoteNotificationsPG/PayloadKeyReference.html#//apple_ref/doc/uid/TP40008194-CH17-SW1
 * @author user
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApnsAlert {

	
	@JsonProperty(value = "title")
	private String title;
	
	@JsonProperty(value = "body")
	private String body;
	
	@JsonProperty(value = "title-loc-key")
	private String titleLocKey;
	
	@JsonProperty(value = "title-loc-args")
	private List<String> titleLocArgs;
	
	@JsonProperty(value = "action-loc-key")
	private String actionLocKey;
	
	@JsonProperty(value = "loc-key")
	private String locKey;
	
	@JsonProperty(value = "loc-args")
	private List<String> locArgs;
	
	@JsonProperty(value = "launch-image")
	private String launchImage;

	public ApnsAlert(String title, String body, String titleLocKey, List<String> titleLocArgs, String actionLocKey,
			String locKey, List<String> locArgs, String launchImage) {
		super();
		this.title = title;
		this.body = body;
		this.titleLocKey = titleLocKey;
		this.titleLocArgs = titleLocArgs;
		this.actionLocKey = actionLocKey;
		this.locKey = locKey;
		this.locArgs = locArgs;
		this.launchImage = launchImage;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public String getTitleLocKey() {
		return titleLocKey;
	}

	public List<String> getTitleLocArgs() {
		return titleLocArgs;
	}

	public String getActionLocKey() {
		return actionLocKey;
	}

	public String getLocKey() {
		return locKey;
	}

	public List<String> getLocArgs() {
		return locArgs;
	}

	public String getLaunchImage() {
		return launchImage;
	}
	
	
}
