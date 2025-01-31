package org.delivery.storeadmin.domain.sse.connection;

import org.delivery.storeadmin.domain.sse.connection.model.UserSSEConnection;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SSEConnectionPool implements ConnectionPoolInterface<String, UserSSEConnection>{

    private static final Map<String, UserSSEConnection> connectionPool = new ConcurrentHashMap<>();

    @Override
    public void addSession(String uniqueKey, UserSSEConnection userSSEConnection) {
        connectionPool.put(uniqueKey, userSSEConnection);
    }

    @Override
    public UserSSEConnection getSession(String uniqueKey) {
        return connectionPool.get(uniqueKey);
    }

    @Override
    public void onCompletionCallback(UserSSEConnection session) {
        connectionPool.remove(session.getUniqueKey());
    }
}
