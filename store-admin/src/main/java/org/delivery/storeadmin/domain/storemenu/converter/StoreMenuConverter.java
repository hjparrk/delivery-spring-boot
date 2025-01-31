package org.delivery.storeadmin.domain.storemenu.converter;

import org.delivery.db.store.StoreMenu;
import org.delivery.storeadmin.common.annotation.Converter;
import org.delivery.storeadmin.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    public StoreMenuResponse toResponse(StoreMenu storeMenu) {
        return StoreMenuResponse.builder()
                .id(storeMenu.getId())
                .name(storeMenu.getName())
                .price(storeMenu.getPrice())
                .thumbnailUrl(storeMenu.getThumbnailUrl())
                .likeCount(storeMenu.getLikeCount())
                .sequence(storeMenu.getSequence())
                .status(storeMenu.getStatus())
                .build();
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenu> storeMenus) {
        return storeMenus.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
