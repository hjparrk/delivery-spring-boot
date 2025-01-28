package org.delivery.api.domain.store.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.StoreCategory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreBusiness {

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(storeConverter::toEntity)
                .map(storeService::register)
                .map(storeConverter::toResponse)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.NULL_POINT, "StoreRegisterRequest cannot be null")
                );
    }

    public List<StoreResponse> searchByCategory(StoreCategory category) {
        return storeService.searchByCategory(category).stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }
 }
