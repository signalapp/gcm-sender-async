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
package org.whispersystems.gcm.server.internal;


import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WrappedListenableFuture<V> implements ListenableFuture<V> {

  private final com.ning.http.client.ListenableFuture<V> wrapped;

  public WrappedListenableFuture(com.ning.http.client.ListenableFuture<V> wrapped) {
    this.wrapped = wrapped;
  }

  @Override
  public void addListener(Runnable runnable, Executor executor) {
    wrapped.addListener(runnable, executor);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return wrapped.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return wrapped.isCancelled();
  }

  @Override
  public boolean isDone() {
    return wrapped.isDone();
  }

  @Override
  public V get() throws InterruptedException, ExecutionException {
    return wrapped.get();
  }

  @Override
  public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    return wrapped.get(timeout, unit);
  }
}
