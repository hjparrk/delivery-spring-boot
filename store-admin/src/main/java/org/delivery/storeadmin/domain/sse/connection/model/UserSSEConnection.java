package org.delivery.storeadmin.domain.sse.connection.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.delivery.storeadmin.domain.sse.connection.ConnectionPoolInterface;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@ToString
@EqualsAndHashCode
public class UserSSEConnection {

    private final String uniqueKey;
    private final SseEmitter sseEmitter;
    private final ObjectMapper objectMapper;
    private final ConnectionPoolInterface<String, UserSSEConnection> connectionPoolInterface;

    private UserSSEConnection(
            String uniqueKey,
            ConnectionPoolInterface<String, UserSSEConnection> connectionPoolInterface,
            ObjectMapper objectMapper
     ) {
        this.uniqueKey = uniqueKey;
        this.sseEmitter = new SseEmitter(60 * 1000L);
        this.objectMapper = objectMapper;
        this.connectionPoolInterface = connectionPoolInterface;

        this.sseEmitter.onCompletion(()->{
            this.connectionPoolInterface.onCompletionCallback(this);
        });
        this.sseEmitter.onTimeout(()->{
            this.sseEmitter.complete();
        });

        sendMessage("onopen", "connect");
    }

    public static UserSSEConnection connect(
            String uniqueKey,
            ConnectionPoolInterface<String, UserSSEConnection> connectionPoolInterface,
            ObjectMapper objectMapper
    ){
        return new UserSSEConnection(uniqueKey, connectionPoolInterface, objectMapper);
    }

    public void sendMessage( Object data)  {
        try {
            var json = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event().data(json);
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(String eventName, Object data) {
        try {
            var json = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event().name(eventName).data(json);
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }
}
