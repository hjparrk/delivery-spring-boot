package org.delivery.api.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.store.Store;
import org.delivery.db.store.StoreCategory;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.StoreStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store register(Store store) {
        if (store == null) throw new ApiException(ErrorCode.NULL_POINT);
        store.setStar(0);
        store.setStatus(StoreStatus.REGISTERED);
        return storeRepository.save(store);
    }

    public Store getStoreWithThrow(Long id) {
        var store = storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED);
        return store.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<Store> searchByCategory(StoreCategory category) {
        return storeRepository.findAllByStatusAndCategoryOrderByStarDesc(
                StoreStatus.REGISTERED,
                category
        );
    }

    public List<Store> registeredStores() {
        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }
}
