package org.delivery.storeadmin.domain.storemenu.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storemenu.StoreMenuStatus;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreMenuResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private String thumbnailUrl;
    private int likeCount;
    private int sequence;
    private StoreMenuStatus status;
}
