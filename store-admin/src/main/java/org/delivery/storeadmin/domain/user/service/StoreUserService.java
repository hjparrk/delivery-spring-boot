package org.delivery.storeadmin.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.db.storeuser.StoreUser;
import org.delivery.db.storeuser.StoreUserRepository;
import org.delivery.db.storeuser.StoreUserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreUserService {

    private final StoreUserRepository storeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public StoreUser register(StoreUser storeUser) {
        storeUser.setStatus(StoreUserStatus.REGISTERED);
        storeUser.setPassword(passwordEncoder.encode(storeUser.getPassword()));
        storeUser.setCreatedAt(LocalDateTime.now());

        return storeUserRepository.save(storeUser);
    }

    public Optional<StoreUser> getRegisteredUser(String email) {
        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED);
    }
}
