package org.whispersystems.gcm.server.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#Notification
 * @author user
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FcmRequestEntity {

	
	@JsonProperty(value = "message")
	private FcmReqMessage message;

	public FcmRequestEntity() {}
	
	public FcmRequestEntity(FcmReqMessage message) {
		super();
		this.message = message;
	}

	public FcmReqMessage getMessage() {
		return message;
	}

	public static FcmRequestEntity cloneWithNewToken(String fcmId, FcmRequestEntity fcmMessage) {
		FcmReqMessage message = fcmMessage.getMessage();
		return new FcmRequestEntity(
			new FcmReqMessage(
					message.getName(),
					message.getData(),
					message.getNotification(),
					message.getAndroid(),
					fcmId,
					message.getTopic(),
					message.getCondition()
			));
	}
}
