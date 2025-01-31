package org.delivery.storeadmin.domain.order.business;

import lombok.RequiredArgsConstructor;
import org.delivery.common.message.model.OrderMessage;
import org.delivery.storeadmin.common.annotation.Business;
import org.delivery.storeadmin.domain.order.controller.model.OrderDetailsResponse;
import org.delivery.storeadmin.domain.order.converter.OrderConverter;
import org.delivery.storeadmin.domain.order.service.OrderService;
import org.delivery.storeadmin.domain.ordermenu.service.OrderMenuService;
import org.delivery.storeadmin.domain.sse.connection.SSEConnectionPool;
import org.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService;

import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class OrderBusiness {

    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final OrderMenuService orderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final SSEConnectionPool sseConnectionPool;

    public void pushOrder(OrderMessage orderMessage) {
        var order = orderService.getOrder(orderMessage.getOrderId())
                .orElseThrow(()->new RuntimeException("Order Not Found"));
        var orderMenus = orderMenuService.getOrderMenus(order.getId());

        var orderResponse = orderConverter.toResponse(order);
        var storeMenuResponses = orderMenus.stream()
                .map(orderMenu -> storeMenuService.getStoreMenuWithThrow(orderMenu.getStoreMenuId()))
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());

        var pushObj = OrderDetailsResponse.builder()
                .order(orderResponse)
                .storeMenus(storeMenuResponses)
                .build();

        var userConnection = sseConnectionPool.getSession(order.getStoreId().toString());
        userConnection.sendMessage(pushObj);
    }


}
