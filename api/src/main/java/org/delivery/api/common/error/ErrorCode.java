package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeInterface {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "Invalid Request"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "Server Error"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 600, "Null Point Error")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;
}
