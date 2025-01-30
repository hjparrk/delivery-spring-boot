package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.StoreStatus;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = storeUserService.getRegisteredUser(username);
        var store = storeRepository.findFirstByIdAndStatusOrderByIdDesc(
                user.get().getId(), StoreStatus.REGISTERED
        );
        return user.map(u -> UserSession.builder()
                    .userId(u.getId())
                    .email(u.getEmail())
                    .password(u.getPassword())
                    .status(u.getStatus())
                    .role(u.getRole())
                    .createdAt(u.getCreatedAt())
                    .deletedAt(u.getDeletedAt())
                    .lastLoginAt(u.getLastLoginAt())
                    .storeId(store.get().getId())
                    .storeName(store.get().getName())
                    .build())
                    .orElseThrow(()-> new UsernameNotFoundException(username));

    }
}
