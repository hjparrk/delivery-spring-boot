package org.delivery.api.common.exception;

import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeInterface;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionInterface {
    private final ErrorCodeInterface errorCodeInterface;
    private final String description;

    public ApiException(ErrorCodeInterface errorCodeInterface) {
        super(errorCodeInterface.getMessage());
        this.errorCodeInterface = errorCodeInterface;
        this.description = errorCodeInterface.getMessage();
    }

    public ApiException(ErrorCodeInterface errorCodeInterface, String description) {
        super(description);
        this.errorCodeInterface = errorCodeInterface;
        this.description = description;
    }

    public ApiException(ErrorCodeInterface errorCodeInterface, Throwable tx) {
        super(tx);
        this.errorCodeInterface = errorCodeInterface;
        this.description = errorCodeInterface.getMessage();
    }

    public ApiException(ErrorCodeInterface errorCodeInterface, Throwable tx, String description) {
        super(tx);
        this.errorCodeInterface = errorCodeInterface;
        this.description = description;
    }
}
