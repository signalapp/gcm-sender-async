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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.util.concurrent.ListenableFuture;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Response;
import com.nurkiewicz.asyncretry.AsyncRetryExecutor;
import com.nurkiewicz.asyncretry.RetryContext;
import com.nurkiewicz.asyncretry.RetryExecutor;
import com.nurkiewicz.asyncretry.function.RetryCallable;
import org.whispersystems.gcm.server.internal.GcmResponseEntity;
import org.whispersystems.gcm.server.internal.GcmResponseListEntity;
import org.whispersystems.gcm.server.internal.WrappedListenableFuture;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;

public class Sender {

  private static final String PRODUCTION_URL = "https://android.googleapis.com/gcm/send";

  private final AsyncHttpClient client;
  private final String          authorizationHeader;
  private final RetryExecutor   executor;
  private final String          url;

  public Sender(String apiKey) {
    this(apiKey, 10);
  }

  public Sender(String apiKey, int retryCount) {
    this(apiKey, retryCount, PRODUCTION_URL);
  }

  @VisibleForTesting
  public Sender(String apiKey, int retryCount, String url) {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    this.url                 = url;
    this.authorizationHeader = String.format("key=%s", apiKey);
    this.client              = new AsyncHttpClient(new AsyncHttpClientConfig.Builder()
                                                       .setMaxRequestRetry(1)
                                                       .build());

    this.executor            = new AsyncRetryExecutor(scheduler).retryOn(ServerFailedException.class)
                                                                .retryOn(TimeoutException.class)
                                                                .retryOn(IOException.class)
                                                                .withExponentialBackoff(100, 2.0)
                                                                .withUniformJitter()
                                                                .withMaxDelay(4000)
                                                                .withMaxRetries(retryCount);
  }

  public ListenableFuture<Result> send(Message message) {
    return send(message, null);
  }

  public ListenableFuture<Result> send(final Message message, final Object requestContext) {
    return executor.getFutureWithRetry(new RetryCallable<ListenableFuture<Result>>() {
      @Override
      public ListenableFuture<Result> call(RetryContext context) throws Exception {
        return new WrappedListenableFuture<>(client.preparePost(url)
                                                   .addHeader("Authorization", authorizationHeader)
                                                   .addHeader("Content-Type", "application/json")
                                                   .setBody(message.serialize())
                                                   .execute(new ResponseHandler(requestContext)));
      }
    });
  }

  public void stop() {
    this.client.close();
  }

  private static final class ResponseHandler extends AsyncCompletionHandler<Result> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private final Object requestContext;

    public ResponseHandler(Object requestContext) {
      this.requestContext = requestContext;
    }

    @Override
    public Result onCompleted(Response response)
        throws InvalidRequestException, AuthenticationFailedException, ServerFailedException
    {
      switch (response.getStatusCode()) {
        case 400: throw new InvalidRequestException();
        case 401: throw new AuthenticationFailedException();
        case 204:
        case 200: return parseResult(response);
        default:  throw new ServerFailedException("Bad status: " + response.getStatusCode());
      }
    }

    private Result parseResult(Response response) throws ServerFailedException {
      try {
        List<GcmResponseEntity> responseList = objectMapper.readValue(response.getResponseBody(),
                                                                      GcmResponseListEntity.class)
                                                           .getResults();

        if (responseList == null || responseList.size() == 0) {
          throw new ServerFailedException("Empty response list!");
        }

        GcmResponseEntity responseEntity = responseList.get(0);

        return new Result(this.requestContext,
                          responseEntity.getCanonicalRegistrationId(),
                          responseEntity.getMessageId(),
                          responseEntity.getError());

      } catch (IOException e) {
        throw new ServerFailedException(e);
      }
    }
  }
}
