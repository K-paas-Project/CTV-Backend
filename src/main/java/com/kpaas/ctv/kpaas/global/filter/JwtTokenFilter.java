package com.kpaas.ctv.kpaas.global.filter;

import com.kpaas.ctv.kpaas.domain.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey
}
