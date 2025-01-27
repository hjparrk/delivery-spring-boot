package org.delivery.api.domain.token.interfaces;

import org.delivery.api.domain.token.model.TokenDto;

import java.util.Map;

public interface TokenHelperInterface {
    TokenDto issueAccessToken(Map<String, Object> data);
    TokenDto issueRefreshToken(Map<String, Object> data);
    Map<String, Object> validateTokenWithThrow(String token);
}
