package org.sopt.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.global.exception.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (token != null) {
            try {
                if (JwtProvider.validateToken(token)) {
                    Authentication authentication = getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (CustomException e) {
                log.debug("토큰 검증 실패: {}", e.getMessage());
                request.setAttribute("exception", e.getErrorCode().name());
                throw e;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("authorization");
        String validTokenPrefix = "Bearer ";
        if (authorization == null || !authorization.startsWith(validTokenPrefix)) {
            return null;
        }
        return authorization.substring(validTokenPrefix.length()).trim();
    }

    private Authentication getAuthentication(String token) {
        Long usIdx = jwtProvider.getUserId(token);
        return new JwtTokenAuthentication(usIdx);
    }
}
