package org.delivery.storeadmin.domain.sse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.sse.connection.SSEConnectionPool;
import org.delivery.storeadmin.domain.sse.connection.model.UserSSEConnection;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/sse")
@RequiredArgsConstructor
public class SSEController {

    private final SSEConnectionPool sseConnectionPool;
    private final ObjectMapper objectMapper;

    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession
    ) {
        log.info("Logged-in user : {}", userSession);
        var sseUserConnection = UserSSEConnection.connect(
                userSession.getUserId().toString(),
                sseConnectionPool,
                objectMapper
        );
        sseConnectionPool.addSession(sseUserConnection.getUniqueKey(), sseUserConnection);
        return sseUserConnection.getSseEmitter();
    }

    @GetMapping(path = "/push-event")
    public void pushEvent(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession
    ) {
        var sseUserConnection = sseConnectionPool.getSession(
                userSession.getUserId().toString()
        );
        Optional.ofNullable(sseUserConnection)
                .ifPresent(session -> {
                    session.sendMessage("hello world");
                });
    }
}