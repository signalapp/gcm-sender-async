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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.whispersystems.gcm.server.internal.GcmRequestEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Message {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private final String              collapseKey;
  private final Long                ttl;
  private final Boolean             delayWhileIdle;
  private final Map<String, String> notification;
  private final Map<String, String> data;
  private final List<String>        registrationIds;

  private Message(String collapseKey, Long ttl, Boolean delayWhileIdle,
                  Map<String, String> notification, Map<String, String> data, List<String> registrationIds)
  {
    this.collapseKey     = collapseKey;
    this.ttl             = ttl;
    this.delayWhileIdle  = delayWhileIdle;
    this.notification    = notification;
    this.data            = data;
    this.registrationIds = registrationIds;
  }

  public String serialize() throws JsonProcessingException {
    GcmRequestEntity requestEntity = new GcmRequestEntity(collapseKey, ttl, delayWhileIdle,
                                                          notification, data, registrationIds);

    return objectMapper.writeValueAsString(requestEntity);
  }

  /**
   * Construct a new Message using a Builder.
   * @return A new Builder.
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {

    private static final String NOTIFICATION_TITLE = "title";
    private static final String NOTIFICATION_BODY = "body";
    private static final String NOTIFICATION_ICON = "icon";
    private static final String NOTIFICATION_SOUND = "sound";
    private static final String NOTIFICATION_BADGE = "badge";
    private static final String NOTIFICATION_TAG = "tag";
    private static final String NOTIFICATION_COLOR = "color";
    private static final String NOTIFICATION_CLICK_ACTION = "click_action";
    private static final String NOTIFICATION_TITLE_LOC_KEY = "title_loc_key";
    private static final String NOTIFICATION_TITLE_LOC_ARGS = "title_loc_args";
    private static final String NOTIFICATION_BODY_LOC_KEY = "body_loc_key";
    private static final String NOTIFICATION_BODY_LOC_ARGS = "body_loc_args";

    private String              collapseKey     = null;
    private Long                ttl             = null;
    private Boolean             delayWhileIdle  = null;
    private Map<String, String> notification    = null;
    private Map<String, String> data            = null;
    private List<String>        registrationIds = new LinkedList<>();

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
     * Set the title in the notification payload automatically displayed by GCM (optional).
     * @param title The title to set.
     * @return The Builder.
     */
    public Builder withNotificationTitle(String title) {
      return withNotificationPart(NOTIFICATION_TITLE, title);
    }

    /**
     * Set the body in the notification payload automatically displayed by GCM (optional).
     * @param body The body to set.
     * @return The Builder.
     */
    public Builder withNotificationBody(String body) {
      return withNotificationPart(NOTIFICATION_BODY, body);
    }

    /**
     * Set the icon in the notification payload automatically displayed by GCM (optional).
     * @param icon The icon to set.
     * @return The Builder.
     */
    public Builder withNotificationIcon(String icon) {
      return withNotificationPart(NOTIFICATION_ICON, icon);
    }

    /**
     * Set the sound in the notification payload automatically displayed by GCM (optional).
     * @param sound The sound to set.
     * @return The Builder.
     */
    public Builder withNotificationSound(String sound) {
      return withNotificationPart(NOTIFICATION_SOUND, sound);
    }

    /**
     * Set the badge in the notification payload automatically displayed by GCM (optional).
     * @param badge The badge to set.
     * @return The Builder.
     */
    public Builder withNotificationBadge(String badge) {
      return withNotificationPart(NOTIFICATION_BADGE, badge);
    }

    /**
     * Set the tag in the notification payload automatically displayed by GCM (optional).
     * @param tag The tag to set.
     * @return The Builder.
     */
    public Builder withNotificationTag(String tag) {
      return withNotificationPart(NOTIFICATION_TAG, tag);
    }

    /**
     * Set the color in the notification payload automatically displayed by GCM (optional).
     * @param color The color to set.
     * @return The Builder.
     */
    public Builder withNotificationColor(String color) {
      return withNotificationPart(NOTIFICATION_COLOR, color);
    }

    /**
     * Set the click_action in the notification payload automatically displayed by GCM (optional).
     * @param clickAction The click_action to set.
     * @return The Builder.
     */
    public Builder withNotificationClickAction(String clickAction) {
      return withNotificationPart(NOTIFICATION_CLICK_ACTION, clickAction);
    }

    /**
     * Set the title_loc_key in the notification payload automatically displayed by GCM (optional).
     * @param titleLocKey The title_loc_key to set.
     * @return The Builder.
     */
    public Builder withNotificationTitleLocKey(String titleLocKey) {
      return withNotificationPart(NOTIFICATION_TITLE_LOC_KEY, titleLocKey);
    }

    /**
     * Set the title_loc_args in the notification payload automatically displayed by GCM (optional).
     * @param titleLocArgs The title_loc_args to set.
     * @return The Builder.
     */
    public Builder withNotificationTitleLocArgs(String titleLocArgs) {
      return withNotificationPart(NOTIFICATION_TITLE_LOC_ARGS, titleLocArgs);
    }

    /**
     * Set the body_loc_key in the notification payload automatically displayed by GCM (optional).
     * @param bodyLocKey The body_loc_key to set.
     * @return The Builder.
     */
    public Builder withNotificationBodyLocKey(String bodyLocKey) {
      return withNotificationPart(NOTIFICATION_BODY_LOC_KEY, bodyLocKey);
    }

    /**
     * Set body_loc_args in the notification payload automatically displayed by GCM (optional).
     * @param bodyLocArgs The body_loc_args to set.
     * @return The Builder.
     */
    public Builder withNotificationBodyLocArgs(String bodyLocArgs) {
      return withNotificationPart(NOTIFICATION_BODY_LOC_ARGS, bodyLocArgs);
    }

    /**
     * Set a key in the notification payload automatically displayed by GCM (optional).
     * @param key The key to set.
     * @param value The value to set.
     * @return The Builder.
     */
    private Builder withNotificationPart(String key, String value) {
      if (notification == null) {
        notification = new HashMap<>();
      }
      notification.put(key, value);
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
     * Construct a message object.
     *
     * @return An immutable message object, as configured by this builder.
     */
    public Message build() {
      if (registrationIds.isEmpty()) {
        throw new IllegalArgumentException("You must specify a destination!");
      }

      return new Message(collapseKey, ttl, delayWhileIdle, notification, data, registrationIds);
    }
  }


}
