package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * Handles user registration by processing the given request data.
     *
     * @param request the user registration request containing name, email, password, and address
     * @return a `UserResponse` containing user details after successful registration
     * @throws NullPointerException if the request is null
     */
    public UserResponse register(UserRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.NULL_POINT, "UserRegisterRequest cannot be null")
                );
    }

    public TokenResponse login(UserLoginRequest request) {
        var user = userService.login(request);
        return tokenBusiness.issueToken(user);
    }
}
