package org.delivery.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.interfaces.TokenHelperInterface;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperInterface tokenHelperInterface;


    public TokenDto issueAccessToken(Long userId) {
        var claims = new HashMap<String, Object>();
        claims.put("userId", userId);

        return tokenHelperInterface.issueAccessToken(claims);
    }

    public TokenDto issueRefreshToken(Long userId) {
        var claims = new HashMap<String, Object>();
        claims.put("userId", userId);

        return tokenHelperInterface.issueRefreshToken(claims);
    }

    public Long validateToken(String token) {
        var claims = tokenHelperInterface.validateTokenWithThrow(token);
        var userId = claims.get("userId");
        if (userId == null) throw new ApiException(ErrorCode.NULL_POINT);

        return Long.parseLong(userId.toString());
    }
}
