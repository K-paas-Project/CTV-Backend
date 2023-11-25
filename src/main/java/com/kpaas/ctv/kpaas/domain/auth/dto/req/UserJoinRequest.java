package com.kpaas.ctv.kpaas.domain.auth.dto.req;


public record UserJoinRequest(
   String userId, String password, String userName
) {}
