
# gcm-sender-async

An asynchronous HTTP library for Google Cloud Messaging.

Google provides a library for GCM (`gcm-server`), but it has a synchronous interface,
along with an implementation that leaves something to be desired.

This is asynchronous, backed by Apache HttpAsyncClient, automatically handles asynchronous
retries with backoff, simple to use, and fast.

## Installing

Add to your pom:

```
<dependency>
  <groupId>org.whispersystems</groupId>
  <artifactId>gcm-sender-async</artifactId>
  <version>(latest version here)</version>
</dependency>
```

## Using

```
String apiKey = "<myGcmApiKey>";
Sender sender = new Sender(apiKey);

ListenableFuture<Result> future = sender.send(Message.newBuilder()
                                                     .withDestination("<registration_id>")
                                                     .withDataPart("message", "hello world!");

Futures.addCallback(future, new FutureCallback<Result>() {
  @Override
  public void onSuccess(Result result) {
    if (result.isSuccess()) {
      // Maybe do something with result.getMessageId()
    } else {
      // Maybe do something with result.getError(), or check result.isUnregistered, etc..
    }
  }

  @Override
  public void onFailure(Throwable throwable) {
    // Handle network failure or server 500
  }
}
```

License
---------------------

Copyright 2013-2019 Open Whisper Systems

Licensed under the AGPLv3: https://www.gnu.org/licenses/agpl-3.0.html
