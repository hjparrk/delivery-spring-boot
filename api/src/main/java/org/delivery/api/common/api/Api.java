package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeInterface;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data) {
        return Api.<T>builder()
                .result(Result.OK())
                .body(data)
                .build();
    }

    public static Api<Object> ERROR(Result result) {
        return Api.<Object>builder()
                .result(result)
                .build();
    }

    public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface) {
        return Api.<Object>builder()
                .result(Result.ERROR(errorCodeInterface))
                .build();
    }

    public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface, String message) {
        return Api.<Object>builder()
                .result(Result.ERROR(errorCodeInterface, message))
                .build();
    }

    public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface, Throwable tx) {
        return Api.<Object>builder()
                .result(Result.ERROR(errorCodeInterface, tx))
                .build();
    }
}
