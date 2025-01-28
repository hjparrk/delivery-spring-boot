package org.delivery.api.domain.store.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.store.StoreCategory;
import org.delivery.db.store.StoreStatus;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {

    private Long id;
    private String name;
    private String address;
    private StoreCategory category;
    private double star;
    private String thumbnailUrl;
    private BigDecimal minPrice;
    private BigDecimal minDeliveryPrice;
    private String phoneNumber;
    private StoreStatus status;
}
