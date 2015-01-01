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

public class Result {

  private final Object context;
  private final String canonicalRegistrationId;
  private final String messageId;
  private final String error;

  public Result(Object context, String canonicalRegistrationId, String messageId, String error) {
    this.context                 = context;
    this.canonicalRegistrationId = canonicalRegistrationId;
    this.messageId               = messageId;
    this.error                   = error;
  }

  public String getCanonicalRegistrationId() {
    return canonicalRegistrationId;
  }

  public boolean hasCanonicalRegistrationId() {
    return canonicalRegistrationId != null && !canonicalRegistrationId.isEmpty();
  }

  public String getMessageId() {
    return messageId;
  }

  public String getError() {
    return error;
  }

  public boolean isSuccess() {
    return messageId != null && !messageId.isEmpty() && (error == null || error.isEmpty());
  }

  public boolean isUnregistered() {
    return "NotRegistered".equals(error);
  }

  public boolean isThrottled() {
    return "DeviceMessageRateExceeded".equals(error);
  }

  public boolean isInvalidRegistrationId() {
    return "InvalidRegistration".equals(error);
  }

  public Object getContext() {
    return context;
  }
}
