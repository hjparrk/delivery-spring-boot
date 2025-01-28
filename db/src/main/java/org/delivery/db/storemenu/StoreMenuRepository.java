package org.delivery.db.storemenu;

import org.delivery.db.store.StoreMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long> {

    Optional<StoreMenu> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreMenuStatus status);

    List<StoreMenu> findAllByStoreIdAndStatusOrderBySequenceDesc(Long id, StoreMenuStatus status);
}
