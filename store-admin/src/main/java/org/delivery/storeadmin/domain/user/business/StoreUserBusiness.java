package org.delivery.storeadmin.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.StoreStatus;
import org.delivery.storeadmin.common.annotation.Business;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.user.service.StoreUserService;

@Business
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserService storeUserService;
    private final StoreUserConverter storeUserConverter;
    private final StoreRepository storeRepository;

    public StoreUserResponse register(StoreUserRegisterRequest request) {
        var store = storeRepository.findFirstByNameAndStatusOrderByIdDesc(
                request.getStoreName(), StoreStatus.REGISTERED).get();
        var storeUser = storeUserConverter.toEntity(request, store);
        var savedStoreUser = storeUserService.register(storeUser);
        return storeUserConverter.toResponse(savedStoreUser, store);
    }
}
