package org.delivery.storeadmin.domain.sse.connection;


public interface ConnectionPoolInterface<T, R> {

    void addSession(T uniqueKey, R session);
    R getSession(T uniqueKey);

    void onCompletionCallback(R session);

}
