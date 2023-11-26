package com.kpaas.ctv.kpaas.domain.auth.dto.req;


public record UserJoinRequest(String userAccount, String password, String userName, String organization
) {}
