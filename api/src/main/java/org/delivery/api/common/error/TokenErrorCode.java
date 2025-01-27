package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Token-related error codes are strictly limited to the 1100 - 1199 range.
 **/
@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCodeInterface{
    TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), 1100, "Unknown Token Error"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), 1101, "Invalid Token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), 1102, "Expired Token")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String message;
}
