package com.kpaas.ctv.kpaas.domain.auth.presention;

import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserJoinRequest;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserLoginRequest;
import com.kpaas.ctv.kpaas.domain.auth.service.UserService;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
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
