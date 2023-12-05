package com.kpaas.ctv.kpaas.domain.auth.presention;

import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserJoinRequest;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserLoginRequest;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserRefreshRequest;
import com.kpaas.ctv.kpaas.domain.auth.service.UserService;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "로그인, 회원가입 api", description = "로그인, 회원가입 api입니다.")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<BaseResponse> join(@RequestBody UserJoinRequest userJoinRequest){
       return userService.join(userJoinRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse> refreshToAccessToken(@RequestBody UserRefreshRequest userRefreshRequest){
        return userService.refreshToAccessToken(userRefreshRequest);
    }

    @GetMapping("/profile")
    public ResponseEntity<BaseResponse> myProfile(Authentication authentication){
        return userService.myProfile(authentication);
    }

}
