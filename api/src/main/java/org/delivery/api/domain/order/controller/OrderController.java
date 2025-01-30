package org.delivery.api.domain.order.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.order.business.OrderBusiness;
import org.delivery.api.domain.order.controller.model.OrderDetailsResponse;
import org.delivery.api.domain.order.controller.model.OrderRequest;
import org.delivery.api.domain.order.controller.model.OrderResponse;
import org.delivery.api.domain.user.model.UserObj;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderBusiness orderBusiness;

    @PostMapping("")
    public Api<OrderResponse> order(
            @Valid
            @RequestBody Api<OrderRequest> request,
            @Parameter(hidden = true)
            @UserSession UserObj user
    ) {
        var response = orderBusiness.order(user, request.getBody());
        return Api.OK(response);
    }

    @GetMapping("/on-going")
    public Api<List<OrderDetailsResponse>> getOngoingOrders(
            @Parameter(hidden = true)
            @UserSession UserObj user
    ) {
        var response = orderBusiness.getOngoingOrders(user);
        return Api.OK(response);
    }

    @GetMapping("/history")
    public Api<List<OrderDetailsResponse>> getHistory(
            @Parameter(hidden = true)
            @UserSession UserObj user
    ) {
        var response = orderBusiness.getHistory(user);
        return Api.OK(response);
    }

    @GetMapping("/id/{id}")
    public Api<OrderDetailsResponse> read(
            @Parameter(hidden = true)
            @UserSession UserObj user,
            @PathVariable
            Long id
    ) {
        var response = orderBusiness.read(user, id);
        return Api.OK(response);
    }
}
