package org.delivery.api.domain.ordermenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.ordermenu.OrderMenuRepository;
import org.delivery.db.ordermenu.OrderMenuStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;

    public List<OrderMenu> getOrderMenus(Long orderId) {
        return orderMenuRepository.findAllByOrderIdAndStatus(orderId, OrderMenuStatus.REGISTERED);
    }

    public OrderMenu order(OrderMenu orderMenu) {
        return Optional.ofNullable(orderMenu)
                .map(om -> {
                    om.setStatus(OrderMenuStatus.REGISTERED);
                    return orderMenuRepository.save(om);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }
}
