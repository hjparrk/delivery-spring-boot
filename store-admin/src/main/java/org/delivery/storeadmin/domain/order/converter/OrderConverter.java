package org.delivery.storeadmin.domain.order.converter;

import org.delivery.db.order.Order;
import org.delivery.storeadmin.common.annotation.Converter;
import org.delivery.storeadmin.domain.order.controller.model.OrderResponse;

@Converter
public class OrderConverter {

    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .storeId(order.getStoreId())
                .price(order.getPrice())
                .status(order.getStatus())
                .orderedAt(order.getOrderedAt())
                .acceptedAt(order.getAcceptedAt())
                .cookingStartedAt(order.getCookingStartedAt())
                .deliveryStartedAt(order.getDeliveryStartedAt())
                .receivedAt(order.getReceivedAt())
                .build();
    }
}
