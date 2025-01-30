package org.delivery.api.domain.order.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.order.controller.model.OrderResponse;
import org.delivery.api.domain.user.model.UserObj;
import org.delivery.db.order.Order;
import org.delivery.db.store.StoreMenu;

import java.math.BigDecimal;
import java.util.List;

@Converter
public class OrderConverter {

    public Order toEntity(UserObj user, List<StoreMenu> storeMenus) {
        var totalPrice = storeMenus.stream()
                .map(StoreMenu::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Order.builder()
                .userId(user.getId())
                .price(totalPrice)
                .build();
    }

    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
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
