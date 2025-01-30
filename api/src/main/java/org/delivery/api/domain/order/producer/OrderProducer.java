package org.delivery.api.domain.order.producer;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.rabbitmq.Producer;
import org.delivery.common.message.model.OrderMessage;
import org.delivery.db.order.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final Producer producer;
    private static final String EXCHANGE = "delivery.exchange";
    private static final String ROUTE_KEY = "delivery.key";

    public void sendOrder(Order order) {
        sendOrder(order.getId());
    }

    public void sendOrder(Long orderId) {
        var message = OrderMessage.builder()
                .orderId(orderId)
                .build();
        producer.producer(EXCHANGE, ROUTE_KEY, message);
    }
}
