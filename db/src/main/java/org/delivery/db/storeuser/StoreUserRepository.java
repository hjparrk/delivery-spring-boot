package org.delivery.db.storeuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreUserRepository extends JpaRepository<StoreUser, Long> {

    Optional<StoreUser> findFirstByEmailAndStatusOrderByIdDesc(String email, StoreUserStatus status);
}
