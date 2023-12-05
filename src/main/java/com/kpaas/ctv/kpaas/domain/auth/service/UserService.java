package com.kpaas.ctv.kpaas.domain.auth.service;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserJoinRequest;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserLoginRequest;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserRefreshRequest;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

public interface UserService {



    ResponseEntity<BaseResponse> join(UserJoinRequest userJoinRequest);

    ResponseEntity<BaseResponse> login(UserLoginRequest userLoginRequest);

    ResponseEntity<BaseResponse> refreshToAccessToken(UserRefreshRequest userRefreshRequest);

    UserEntity getUserByUserAccount(String userAccount);


    default UserEntity reqToEntity(UserJoinRequest request){

        return UserEntity.builder()
                .userAccount(request.userAccount())
                .password(request.password())
                .userName(request.userName())
                .organization(request.organization())
                .build();
    }
}
