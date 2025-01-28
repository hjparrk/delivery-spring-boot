package org.delivery.api.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.store.StoreMenu;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.StoreMenuStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenu register(StoreMenu storeMenu) {
        if (storeMenu == null) throw new ApiException(ErrorCode.NULL_POINT);

        storeMenu.setStatus(StoreMenuStatus.REGISTERED);
        return storeMenuRepository.save(storeMenu);
    }

    public StoreMenu getStoreMenuWithThrow(Long id) {
        var storeMenu = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED);
        return storeMenu.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenu> getStoreMenuByStoreId(Long storeId) {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
    }
}
