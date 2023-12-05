package com.kpaas.ctv.kpaas.domain.auth.dto.res;

import lombok.Builder;

@Builder
public record UserResponse(String userAccount, String userName, String organization) {
}
