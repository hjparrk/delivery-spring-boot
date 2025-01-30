package org.delivery.storeadmin.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.Store;
import org.delivery.db.storeuser.StoreUser;
import org.delivery.storeadmin.common.annotation.Converter;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;

@Converter
@RequiredArgsConstructor
public class StoreUserConverter {

    public StoreUser toEntity(StoreUserRegisterRequest request, Store store) {
        return StoreUser.builder()
                .storeId(store.getId())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }

    public StoreUserResponse toResponse(StoreUser user, Store store) {
        var userResponse = StoreUserResponse.UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .deletedAt(user.getDeletedAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();
        var storeResponse = StoreUserResponse.StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .build();

        return StoreUserResponse.builder()
                .user(userResponse)
                .store(storeResponse)
                .build();
    }

    public StoreUserResponse toResponse(UserSession userSession) {
        var userResponse = StoreUserResponse.UserResponse.builder()
                .id(userSession.getUserId())
                .email(userSession.getEmail())
                .role(userSession.getRole())
                .status(userSession.getStatus())
                .createdAt(userSession.getCreatedAt())
                .deletedAt(userSession.getDeletedAt())
                .lastLoginAt(userSession.getLastLoginAt())
                .build();
        var storeResponse = StoreUserResponse.StoreResponse.builder()
                .id(userSession.getStoreId())
                .name(userSession.getStoreName())
                .build();

        return StoreUserResponse.builder()
                .user(userResponse)
                .store(storeResponse)
                .build();
    }
}
