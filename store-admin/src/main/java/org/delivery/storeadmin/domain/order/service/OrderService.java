package org.delivery.storeadmin.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.delivery.db.order.Order;
import org.delivery.db.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
