package org.delivery.api.domain.store.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.store.StoreCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    private StoreCategory category;
    @NotBlank
    private String thumbnailUrl;
    @NotNull
    private BigDecimal minPrice;
    @NotNull
    private BigDecimal minDeliveryPrice;
    @NotBlank
    private String phoneNumber;
}
