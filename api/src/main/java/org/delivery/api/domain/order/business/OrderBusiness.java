package org.delivery.api.domain.order.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.order.controller.model.OrderDetailsResponse;
import org.delivery.api.domain.order.controller.model.OrderRequest;
import org.delivery.api.domain.order.controller.model.OrderResponse;
import org.delivery.api.domain.order.converter.OrderConverter;
import org.delivery.api.domain.order.producer.OrderProducer;
import org.delivery.api.domain.order.service.OrderService;
import org.delivery.api.domain.ordermenu.converter.OrderMenuConverter;
import org.delivery.api.domain.ordermenu.service.OrderMenuService;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.UserObj;
import org.delivery.db.order.Order;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class OrderBusiness {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    private final OrderMenuService orderMenuService;
    private final OrderMenuConverter orderMenuConverter;

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    private final OrderProducer orderProducer;

    public OrderResponse order(UserObj user, OrderRequest request) {
        // Get StoreMenu entities with storeMenuIds
        var storeId = request.getStoreId();
        var storeMenus = request.getStoreMenuIds().stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .collect(Collectors.toList());

        // Create and save Order entity
        var order = orderConverter.toEntity(user, storeId, storeMenus);
        var savedOrder = orderService.order(order);

        // Create OrderMenu entities
        var orderMenus = storeMenus.stream()
                .map(storeMenu -> orderMenuConverter.toEntity(savedOrder, storeMenu))
                .collect(Collectors.toList());

        // Save OrderMenu entities
        orderMenus.forEach(orderMenuService::order);

        // (Async) Alert to the store
        orderProducer.sendOrder(savedOrder);

        // Return response
        return orderConverter.toResponse(savedOrder);
    }

    private OrderDetailsResponse getOrderDetailsResponse(Order order) {
        var orderMenus = orderMenuService.getOrderMenus(order.getId());
        var storeMenus = orderMenus.stream()
                .map(orderMenu -> storeMenuService.getStoreMenuWithThrow(orderMenu.getStoreMenuId()))
                .collect(Collectors.toList());

        var store = storeService.getStoreWithThrow(storeMenus.stream().findFirst().get().getStoreId());

        return OrderDetailsResponse.builder()
                .order(orderConverter.toResponse(order))
                .store(storeConverter.toResponse(store))
                .storeMenus(storeMenuConverter.toResponses(storeMenus))
                .build();
    }

    private List<OrderDetailsResponse> getOrderDetailsResponses(List<Order> orders) {
        return orders.stream()
                .map(this::getOrderDetailsResponse)
                .collect(Collectors.toList());
    }

    public List<OrderDetailsResponse> getOngoingOrders(UserObj user) {
        var orders = orderService.getOngoingOrders(user.getId());
        return getOrderDetailsResponses(orders);

    }

    public List<OrderDetailsResponse> getHistory(UserObj user) {
        var orders = orderService.getHistory(user.getId());
        return getOrderDetailsResponses(orders);
    }

    public OrderDetailsResponse read(UserObj user, Long id) {
        var order = orderService.getOrderWithoutStatusWithThrow(id, user.getId());
        return getOrderDetailsResponse(order);
    }
}
