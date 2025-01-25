package org.delivery.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.ApiResponse;
import org.delivery.api.common.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse<Object>> apiException(
            ApiException exception
    ) {
        log.error("", exception);
        var errorCode = exception.getErrorCodeInterface();
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(ApiResponse.ERROR(errorCode, exception.getDescription()));
    }
}
