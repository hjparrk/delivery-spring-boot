package org.delivery.storeadmin.domain.order.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.storeadmin.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsResponse {

    private OrderResponse order;
    private List<StoreMenuResponse> storeMenus;
}
