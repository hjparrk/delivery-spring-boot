package org.delivery.storeadmin.domain.order.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.OrderMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    @RabbitListener(queues = "delivery.queue")
    public void orderConsumer(OrderMessage orderMessage) {
        log.info("message queue >>> {}", orderMessage);
    }
}
