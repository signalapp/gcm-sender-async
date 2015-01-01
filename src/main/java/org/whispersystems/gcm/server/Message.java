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
  private final Map<String, String> data;
  private final List<String>        registrationIds;

  private Message(String collapseKey, Long ttl, Boolean delayWhileIdle,
                  Map<String, String> data, List<String> registrationIds)
  {
    this.collapseKey     = collapseKey;
    this.ttl             = ttl;
    this.delayWhileIdle  = delayWhileIdle;
    this.data            = data;
    this.registrationIds = registrationIds;
  }

  public String serialize() throws JsonProcessingException {
    GcmRequestEntity requestEntity = new GcmRequestEntity(collapseKey, ttl, delayWhileIdle,
                                                          data, registrationIds);

    return objectMapper.writeValueAsString(requestEntity);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {

    private String              collapseKey     = null;
    private Long                ttl             = null;
    private Boolean             delayWhileIdle  = null;
    private Map<String, String> data            = null;
    private List<String>        registrationIds = new LinkedList<>();

    private Builder() {}

    public Builder withCollapseKey(String collapseKey) {
      this.collapseKey = collapseKey;
      return this;
    }

    public Builder withTtl(long seconds) {
      this.ttl = seconds;
      return this;
    }

    public Builder withDelayWhileIdle(boolean delayWhileIdle) {
      this.delayWhileIdle = delayWhileIdle;
      return this;
    }

    public Builder withDataPart(String key, String value) {
      if (data == null) {
        data = new HashMap<>();
      }
      data.put(key, value);
      return this;
    }

    public Builder withDestination(String registrationId) {
      this.registrationIds.clear();
      this.registrationIds.add(registrationId);
      return this;
    }

    public Message build() {
      if (registrationIds.isEmpty()) {
        throw new IllegalArgumentException("You must specify a destination!");
      }

      return new Message(collapseKey, ttl, delayWhileIdle, data, registrationIds);
    }
  }


}
