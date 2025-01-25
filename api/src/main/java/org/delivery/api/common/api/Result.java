package org.delivery.api.common.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeInterface;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    private Boolean success;
    private Integer status;
    private String message;
    private String description;

    public static Result OK() {
        return Result.builder()
                .success(true)
                .status(200)
                .message("OK")
                .description("Success")
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface) {
        return Result.builder()
                .success(false)
                .status(errorCodeInterface.getErrorCode())
                .message(errorCodeInterface.getMessage())
                .description("Error")
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, String description) {
        return Result.builder()
                .success(false)
                .status(errorCodeInterface.getErrorCode())
                .message(errorCodeInterface.getMessage())
                .description(description)
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, Throwable tx) {
        return Result.builder()
                .success(false)
                .status(errorCodeInterface.getErrorCode())
                .message(errorCodeInterface.getMessage())
                .description(tx.getLocalizedMessage())
                .build();
    }
}
