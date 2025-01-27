package org.delivery.api.domain.token.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.model.TokenDto;

@Converter
@RequiredArgsConstructor
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessTokenDto,
            TokenDto refreshTokenDto
    ) {
        if (accessTokenDto == null || refreshTokenDto == null)
            throw new ApiException(ErrorCode.NULL_POINT);

        return TokenResponse.builder()
                .accessToken(accessTokenDto.getToken())
                .accessTokenExpiredAt(accessTokenDto.getExpiredAt())
                .refreshToken(refreshTokenDto.getToken())
                .refreshTokenExpiredAt(refreshTokenDto.getExpiredAt())
                .build();
    }
}
