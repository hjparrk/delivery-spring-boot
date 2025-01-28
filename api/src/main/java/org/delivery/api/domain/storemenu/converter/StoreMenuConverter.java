package org.delivery.api.domain.storemenu.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.db.store.StoreMenu;

@Converter
@RequiredArgsConstructor
public class StoreMenuConverter {

    public StoreMenu toEntity(StoreMenuRegisterRequest request) {
        if (request == null) throw new ApiException(ErrorCode.NULL_POINT);

        return StoreMenu.builder()
                .storeId(request.getStoreId())
                .name(request.getName())
                .price(request.getPrice())
                .thumbnailUrl(request.getThumbnailUrl())
                .build();
    }

    public StoreMenuResponse toResponse(StoreMenu storeMenu) {
        if (storeMenu == null) throw new ApiException(ErrorCode.NULL_POINT);

        return StoreMenuResponse.builder()
                .id(storeMenu.getId())
                .storeId(storeMenu.getStoreId())
                .name(storeMenu.getName())
                .price(storeMenu.getPrice())
                .thumbnailUrl(storeMenu.getThumbnailUrl())
                .likeCount(storeMenu.getLikeCount())
                .sequence(storeMenu.getSequence())
                .status(storeMenu.getStatus())
                .build();
    }
}
