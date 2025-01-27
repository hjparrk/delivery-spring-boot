package org.delivery.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.User;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public User toEntity(UserRegisterRequest request) {
        if (request == null) throw new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest cannot be null");

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .build();
    }

    public UserResponse toResponse(User user) {
        if (user == null) throw new ApiException(ErrorCode.NULL_POINT, "User entity cannot be null");

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .deletedAt(user.getDeletedAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
}
