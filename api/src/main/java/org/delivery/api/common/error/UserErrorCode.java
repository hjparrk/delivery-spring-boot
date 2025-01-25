package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeInterface{
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 1001, "User Not Found")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;
}
