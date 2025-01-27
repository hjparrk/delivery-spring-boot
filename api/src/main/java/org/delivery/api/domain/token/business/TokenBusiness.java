package org.delivery.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.db.user.User;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    public TokenResponse issueToken(User user) {
        return Optional.ofNullable(user)
                .map(User::getId)
                .map(userId -> tokenConverter.toResponse(
                        tokenService.issueAccessToken(userId),
                        tokenService.issueRefreshToken(userId)
                ))
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validateAccessToken(String acccessToken) {
        return tokenService.validateToken(acccessToken);
    }
}
