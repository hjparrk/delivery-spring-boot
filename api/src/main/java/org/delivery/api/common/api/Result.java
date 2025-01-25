package org.delivery.api.common.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    private Boolean success;
    private Integer status;
    private String message;

    public static Result OK() {
        return Result.builder()
                .success(true)
                .status(200)
                .message("OK")
                .build();
    }
}
