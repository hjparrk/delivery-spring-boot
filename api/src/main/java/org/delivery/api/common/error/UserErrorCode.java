package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * User-related error codes are strictly limited to the 1000 - 1099 range.
 **/
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeInterface{
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 1000, "User Not Found")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;
}
