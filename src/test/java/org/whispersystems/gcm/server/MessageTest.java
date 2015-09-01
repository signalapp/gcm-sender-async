package org.whispersystems.gcm.server;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.whispersystems.gcm.server.util.JsonHelpers.fromJson;
import static org.whispersystems.gcm.server.util.JsonHelpers.jsonFixture;

public class MessageTest {

  @Test
  public void testMinimal() throws IOException {
    Message message = Message.newBuilder()
                             .withDestination("1")
                             .build();

    assertEquals(message.serialize(), jsonFixture("fixtures/message-minimal.json"));
  }

  @Test
  public void testComplete() throws IOException {
    Message message = Message.newBuilder()
                             .withDestination("1")
                             .withCollapseKey("collapse")
                             .withDelayWhileIdle(true)
                             .withTtl(10)
                             .build();

    assertEquals(fromJson(message.serialize(), HashMap.class),
                 fromJson(jsonFixture("fixtures/message-complete.json"), HashMap.class));
  }

  @Test
  public void testWithData() throws IOException {
    Message message = Message.newBuilder()
                             .withDestination("2")
                             .withDataPart("key1", "value1")
                             .withDataPart("key2", "value2")
                             .build();

    assertEquals(fromJson(message.serialize(), HashMap.class),
                 fromJson(jsonFixture("fixtures/message-data.json"), HashMap.class));
  }

  @Test
  public void testWithNotification() throws IOException {
    Message message = Message.newBuilder()
            .withDestination("3")
            .withNotificationTitle("title")
            .withNotificationBody("body")
            .withNotificationIcon("icon")
            .withNotificationSound("sound")
            .withNotificationBadge("badge")
            .withNotificationTag("tag")
            .withNotificationColor("color")
            .withNotificationClickAction("click-action")
            .withNotificationTitleLocKey("title-loc-key")
            .withNotificationTitleLocArgs("title-loc-args")
            .withNotificationBodyLocKey("body-loc-key")
            .withNotificationBodyLocArgs("body-loc-args")
            .build();

    assertEquals(fromJson(message.serialize(), HashMap.class),
                 fromJson(jsonFixture("fixtures/message-notification.json"), HashMap.class));
  }

  @Test
  public void testWithNotificationAndData() throws IOException {
    Message message = Message.newBuilder()
            .withDestination("3")
            .withNotificationTitle("title")
            .withNotificationBody("body")
            .withNotificationIcon("icon")
            .withNotificationSound("sound")
            .withNotificationBadge("badge")
            .withNotificationTag("tag")
            .withNotificationColor("color")
            .withNotificationClickAction("click-action")
            .withNotificationTitleLocKey("title-loc-key")
            .withNotificationTitleLocArgs("title-loc-args")
            .withNotificationBodyLocKey("body-loc-key")
            .withNotificationBodyLocArgs("body-loc-args")
            .withDataPart("key1", "value1")
            .withDataPart("key2", "value2")
            .build();

    assertEquals(fromJson(message.serialize(), HashMap.class),
                 fromJson(jsonFixture("fixtures/message-notification-and-data.json"), HashMap.class));
  }

}
