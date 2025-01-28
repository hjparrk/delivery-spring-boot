package org.delivery.api.domain.store.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.db.store.Store;

@Converter
@RequiredArgsConstructor
public class StoreConverter {

    public Store toEntity(StoreRegisterRequest request) {
        if (request == null) throw new ApiException(ErrorCode.NULL_POINT);

        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .category(request.getCategory())
                .thumbnailUrl(request.getThumbnailUrl())
                .minPrice(request.getMinPrice())
                .minDeliveryPrice(request.getMinDeliveryPrice())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public StoreResponse toResponse(Store store) {
        if (store == null) throw new ApiException(ErrorCode.NULL_POINT);

        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .category(store.getCategory())
                .star(store.getStar())
                .thumbnailUrl(store.getThumbnailUrl())
                .minPrice(store.getMinPrice())
                .minDeliveryPrice(store.getMinDeliveryPrice())
                .phoneNumber(store.getPhoneNumber())
                .status(store.getStatus())
                .build();
    }
}
