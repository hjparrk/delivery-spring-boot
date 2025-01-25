package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
