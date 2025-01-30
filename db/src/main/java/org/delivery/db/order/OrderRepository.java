package org.delivery.db.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserIdAndStatusOrderByIdDesc(Long userId, OrderStatus status);

    List<Order> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<OrderStatus> statuses);

    Optional<Order> findFirstByIdAndStatusAndUserId(Long id, OrderStatus status, Long userId);

    Optional<Order> findFirstByIdAndUserId(Long id, Long userId);
}
