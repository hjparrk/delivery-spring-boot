package org.delivery.storeadmin.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.storeadmin.domain.user.business.StoreUserBusiness;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public/store-users")
@RequiredArgsConstructor
public class PublicStoreUserController {

    private final StoreUserBusiness storeUserBusiness;

    @PostMapping("")
    public StoreUserResponse register(
            @Valid
            @RequestBody StoreUserRegisterRequest request
    ) {
        var response = storeUserBusiness.register(request);
        return response;
    }
}
