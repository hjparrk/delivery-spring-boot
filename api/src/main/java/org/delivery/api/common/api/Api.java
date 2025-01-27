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
public class ApiResponse<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> ApiResponse<T> OK(T data) {
        return ApiResponse.<T>builder()
                .result(Result.OK())
                .body(data)
                .build();
    }

    public static ApiResponse<Object> ERROR(Result result) {
        return ApiResponse.<Object>builder()
                .result(result)
                .build();
    }

    public static ApiResponse<Object> ERROR(ErrorCodeInterface errorCodeInterface) {
        return ApiResponse.<Object>builder()
                .result(Result.ERROR(errorCodeInterface))
                .build();
    }

    public static ApiResponse<Object> ERROR(ErrorCodeInterface errorCodeInterface, String message) {
        return ApiResponse.<Object>builder()
                .result(Result.ERROR(errorCodeInterface, message))
                .build();
    }

    public static ApiResponse<Object> ERROR(ErrorCodeInterface errorCodeInterface, Throwable tx) {
        return ApiResponse.<Object>builder()
                .result(Result.ERROR(errorCodeInterface, tx))
                .build();
    }
}
