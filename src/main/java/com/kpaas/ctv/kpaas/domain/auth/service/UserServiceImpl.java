package com.kpaas.ctv.kpaas.domain.auth.service;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import com.kpaas.ctv.kpaas.domain.auth.domain.repository.UserRepository;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserJoinRequest;
import com.kpaas.ctv.kpaas.domain.auth.dto.req.UserLoginRequest;
import com.kpaas.ctv.kpaas.domain.auth.exception.AuthErrorDuplicatedException;
import com.kpaas.ctv.kpaas.domain.auth.exception.AuthErrorNotFoundException;
import com.kpaas.ctv.kpaas.domain.auth.exception.AuthErrorNotInIdOrPwException;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import com.kpaas.ctv.kpaas.global.filter.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encode;

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Override
    public ResponseEntity<BaseResponse> join(UserJoinRequest userJoinRequest){
        BaseResponse baseResponse = new BaseResponse();

        if (userJoinRequest.userAccount().isEmpty() || userJoinRequest.password().isEmpty() || userJoinRequest.userName().isEmpty() || userJoinRequest.organization().isEmpty()) {
            throw AuthErrorNotInIdOrPwException.EXCEPTION;
        }

        userRepository.findByUserAccount(userJoinRequest.userAccount()).ifPresent(userEntity -> {
            throw AuthErrorDuplicatedException.EXCEPTION;
        });

        UserEntity user = reqToEntity(userJoinRequest);
        String passwordEncoder = encode.encode(user.getPassword());
        user.fixPassword(passwordEncoder); // 인코딩한 password로 저장
        userRepository.save(user);
        baseResponse.of(HttpStatus.CREATED, "회원가입 성공");
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public ResponseEntity<BaseResponse> login(UserLoginRequest userLoginRequest) {
        BaseResponse baseResponse = new BaseResponse();

        UserEntity user = userRepository.findByUserAccount(userLoginRequest.userAccount()).orElseThrow(() -> AuthErrorNotFoundException.EXCEPTION);
        if (!encode.matches(userLoginRequest.password(), user.getPassword())){
            throw AuthErrorNotFoundException.EXCEPTION;
        }

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", JwtTokenUtil.createAccessToken(user.getUserAccount(), secretKey));
        tokens.put("refreshToken", JwtTokenUtil.createRefreshToken(user.getUserAccount(), secretKey));

        baseResponse.of(HttpStatus.OK, "로그인 성공", tokens);

        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public UserEntity getUserByUserAccount(String userAccount) {
        return userRepository.findByUserAccount(userAccount)
                .orElseThrow(() -> AuthErrorNotFoundException.EXCEPTION);
    }
}
