package org.delivery.api.domain.token.helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.interfaces.TokenHelperInterface;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper implements TokenHelperInterface {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.duration}")
    private Long accessTokenDuration;

    @Value("${token.refresh-token.duration}")
    private Long refreshTokenDuration;

    private byte[] getSigningKey() {
        return secretKey.getBytes();
    }

    private TokenDto issueToken(Map<String, Object> claims, Long duration) {
        var expiredAtLDT = LocalDateTime.now().plusHours(duration);
        var expiredAt = Date.from(expiredAtLDT.atZone(ZoneId.systemDefault()).toInstant());
        var key = Keys.hmacShaKeyFor(getSigningKey());

        var token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenDto.builder()
                .token(token)
                .expiredAt(expiredAtLDT)
                .build();
    }

    @Override
    public TokenDto issueAccessToken(Map<String, Object> claims) {
        return issueToken(claims, accessTokenDuration);
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> claims) {
        return issueToken(claims, refreshTokenDuration);
    }

    @Override
    public Map<String, Object> validateTokenWithThrow(String token) {
        var key = Keys.hmacShaKeyFor(getSigningKey());
        var parser = Jwts.parserBuilder().setSigningKey(key).build();

        try {
            var claims = parser.parseClaimsJws(token).getBody();
            return new HashMap<>(claims);
        } catch (SignatureException e) {
            throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);
        } catch (ExpiredJwtException e) {
            throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);
        } catch (Exception e) {
            throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION, e);
        }
    }
}
