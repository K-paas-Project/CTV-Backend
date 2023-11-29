package com.kpaas.ctv.kpaas.domain.auth.presention;

import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserJoinRequest;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserLoginRequest;
import com.kpaas.ctv.kpaas.domain.auth.service.UserService;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
