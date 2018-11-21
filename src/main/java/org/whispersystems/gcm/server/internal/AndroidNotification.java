package org.whispersystems.gcm.server.internal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AndroidNotification {

	
	@JsonProperty(value = "title")
	private String title;
	
	@JsonProperty(value = "body")
	private String body;
	
	@JsonProperty(value = "icon")
	private String icon;
	
	@JsonProperty(value = "color")
	private String color;
	
	@JsonProperty(value = "sound")
	private String sound;
	
	@JsonProperty(value = "tag")
	private String tag;
	
	@JsonProperty(value = "click_action")
	private String click_action;
	
	@JsonProperty(value = "body_loc_key")
	private String body_loc_key;
	
	@JsonProperty(value = "title_loc_key")
	private String title_loc_key;
	
	@JsonProperty(value = "body_loc_args")
	private List<String> body_loc_args;
	
	@JsonProperty(value = "title_loc_args")
	private List<String> title_loc_args;

	public AndroidNotification(String title, String body, String icon, String color, String sound, String tag,
			String click_action, String body_loc_key, String title_loc_key, List<String> body_loc_args,
			List<String> title_loc_args) {
		super();
		this.title = title;
		this.body = body;
		this.icon = icon;
		this.color = color;
		this.sound = sound;
		this.tag = tag;
		this.click_action = click_action;
		this.body_loc_key = body_loc_key;
		this.title_loc_key = title_loc_key;
		this.body_loc_args = body_loc_args;
		this.title_loc_args = title_loc_args;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public String getIcon() {
		return icon;
	}

	public String getColor() {
		return color;
	}

	public String getSound() {
		return sound;
	}

	public String getTag() {
		return tag;
	}

	public String getClick_action() {
		return click_action;
	}

	public String getBody_loc_key() {
		return body_loc_key;
	}

	public String getTitle_loc_key() {
		return title_loc_key;
	}

	public List<String> getBody_loc_args() {
		return body_loc_args;
	}

	public List<String> getTitle_loc_args() {
		return title_loc_args;
	}
	
}
