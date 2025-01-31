package org.delivery.storeadmin.domain.ordermenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.ordermenu.OrderMenuRepository;
import org.delivery.db.ordermenu.OrderMenuStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;

    public List<OrderMenu> getOrderMenus(Long orderId) {
        return orderMenuRepository.findAllByOrderIdAndStatus(orderId, OrderMenuStatus.REGISTERED);
    }
}
