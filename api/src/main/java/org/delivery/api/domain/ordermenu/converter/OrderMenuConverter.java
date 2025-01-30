package org.delivery.api.domain.ordermenu.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.db.order.Order;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.store.StoreMenu;

@Converter
public class OrderMenuConverter {

    public OrderMenu toEntity(Order order, StoreMenu storeMenu) {
        return OrderMenu.builder()
                .orderId(order.getId())
                .storeMenuId(storeMenu.getId())
                .build();
    }
}
