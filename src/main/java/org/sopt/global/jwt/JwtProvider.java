package org.sopt.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.sopt.global.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static org.sopt.global.exception.ErrorCode.*;

@Slf4j
@Component
public class JwtProvider {
    private static String secret;
    private static long accessExpirationMs;
    private static Key key;

    @Value("${jwt.secret}")
    public void setSecret(String secretValue) {
        secret = secretValue;
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Value("${jwt.accessTokenExpiration}")
    public void setAccessExpirationMs(long value) {
        accessExpirationMs = value;
    }

    /**
     * JWT 발급
     */
    public static String generateToken(Long userId) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiry = new Date(now + accessExpirationMs);

        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    /**
     * JWT 검증 및 파싱
     */
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)                  // 서명 검증을 위한 키 설정
                .build()
                .parseClaimsJws(token)               // JWS 파싱 및 서명 검증
                .getBody();                          // Claims 객체 반환 (payload 안의 내용)
    }

    /**
     * JWT 유효성 체크
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
        } catch (SecurityException | MalformedJwtException e) {
            log.debug("잘못된 JWT 서명입니다.");
            throw new CustomException(INVALID_SIGNATURE);
        } catch (ExpiredJwtException e) {
            log.debug("만료된 토큰입니다.");
            throw new CustomException(TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.debug("지원하지 않는 토큰입니다.");
            throw new CustomException(UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            log.debug("잘못된 토큰입니다.");
            throw new CustomException(INVALID_TOKEN);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new CustomException(INTERNAL_SERVER_ERROR);
        }
        return true;
    }
}


