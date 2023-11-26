package com.kpaas.ctv.kpaas.domain.auth.service;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserJoinRequest;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserLoginRequest;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

public interface UserService {



    ResponseEntity<BaseResponse> join(UserJoinRequest userJoinRequest);

    ResponseEntity<BaseResponse> login(UserLoginRequest userLoginRequest);


    default UserEntity reqToEntity(UserJoinRequest request){

        return UserEntity.builder()
                .userName(request.userAccount())
                .password(request.password())
                .userName(request.userName())
                .organization(request.organization())
                .build();
    }
}
