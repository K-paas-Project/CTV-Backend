package com.kpaas.ctv.kpaas.global.filter.jwt;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import com.kpaas.ctv.kpaas.domain.auth.service.UserService;
import com.kpaas.ctv.kpaas.global.exception.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring(7);
                if (isExpired(token)) throw JwtException.JWT_IS_EXPIRED;
                Claims claims = extractClaims(token);
                String userAccount = claims.get("userAccount", String.class);

                UserEntity user = userService.getUserByUserAccount(userAccount);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user.getUserAccount(), null,
                                List.of(new SimpleGrantedAuthority(user.getRole().name())));

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } catch (MalformedJwtException e) {
                throw JwtException.JWT_NOT_FOUND_EXCEPTION;
            } catch (ExpiredJwtException e) {//유효 기간이 지난 JWT를 수신한 경우
                throw JwtException.JWT_IS_EXPIRED;
            } catch(Exception e) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody();
    }
    private boolean isExpired(String token){
        try{
            Claims claims = extractClaims(token);
            Date expirationDate = claims.getExpiration();
            return expirationDate != null && expirationDate.before(new Date());
        } catch (ExpiredJwtException e) {
            throw JwtException.JWT_IS_EXPIRED;
        } catch (Exception e) {
            throw JwtException.JWT_NOT_FOUND_EXCEPTION;
        }
    }
}
