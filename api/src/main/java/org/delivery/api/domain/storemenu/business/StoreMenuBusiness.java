package org.delivery.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public StoreMenuResponse register(StoreMenuRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(storeMenuConverter::toEntity)
                .map(storeMenuService::register)
                .map(storeMenuConverter::toResponse)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.NULL_POINT, "StoreMenuRegisterRequest cannot be null")
                );
    }

    public List<StoreMenuResponse> search(Long storeId) {
        return storeMenuService.getStoreMenuByStoreId(storeId).stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }
}
