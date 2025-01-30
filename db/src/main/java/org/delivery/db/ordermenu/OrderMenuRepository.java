package org.delivery.db.ordermenu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {

    List<OrderMenu> findAllByOrderIdAndStatus(Long orderId, OrderMenuStatus status);
}
