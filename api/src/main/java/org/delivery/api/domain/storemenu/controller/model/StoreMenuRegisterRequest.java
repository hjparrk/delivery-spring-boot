package org.delivery.api.domain.storemenu.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreMenuRegisterRequest {

    @NotNull
    private Long storeId;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String thumbnailUrl;
}
