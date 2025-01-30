package org.delivery.api.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.order.Order;
import org.delivery.db.order.OrderRepository;
import org.delivery.db.order.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order getOrderWithoutStatusWithThrow(Long id, Long userId) {
        return orderRepository.findFirstByIdAndUserId(id, userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public Order getOrderWithThrow(Long id, Long userId) {
        return orderRepository.findFirstByIdAndStatusAndUserId(id, OrderStatus.REGISTERED, userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, OrderStatus.REGISTERED);
    }

    public List<Order> getOrders(Long userId, List<OrderStatus> statuses) {
        return orderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statuses);
    }

    public List<Order> getOngoingOrders(Long userId) {
        var statuses = List.of(
                OrderStatus.ORDERED,
                OrderStatus.ACCEPTED,
                OrderStatus.COOKING,
                OrderStatus.DELIVERING
        );
        return getOrders(userId, statuses);
    }

    public List<Order> getHistory(Long userId) {
        return getOrders(userId, List.of(OrderStatus.RECEIVED));
    }

    public Order setStatus(Order order, OrderStatus status) {
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order order(Order order) {
        if (order == null) throw new ApiException(ErrorCode.NULL_POINT);

        order.setOrderedAt(LocalDateTime.now());
        return setStatus(order, OrderStatus.ORDERED);
    }

    public Order accept(Order order) {
        if (order == null) throw new ApiException(ErrorCode.NULL_POINT);

        order.setAcceptedAt(LocalDateTime.now());
        return setStatus(order, OrderStatus.ACCEPTED);
    }

    public Order cook(Order order) {
        if (order == null) throw new ApiException(ErrorCode.NULL_POINT);

        order.setCookingStartedAt(LocalDateTime.now());
        return setStatus(order, OrderStatus.COOKING);
    }

    public Order deliver(Order order) {
        if (order == null) throw new ApiException(ErrorCode.NULL_POINT);

        order.setDeliveryStartedAt(LocalDateTime.now());
        return setStatus(order, OrderStatus.DELIVERING);
    }

    public Order receive(Order order) {
        if (order == null) throw new ApiException(ErrorCode.NULL_POINT);

        order.setReceivedAt(LocalDateTime.now());
        return setStatus(order, OrderStatus.RECEIVED);
    }

}
