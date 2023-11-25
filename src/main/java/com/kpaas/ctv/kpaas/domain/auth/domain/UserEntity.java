package com.kpaas.ctv.kpaas.domain.auth.domain;

import com.kpaas.ctv.kpaas.global.common.constant.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    private Long id;

    @Column
    private String userId;

    @Column
    private String password;

    @Column
    private String userName;

    @Column
    private Role role;

    @Builder
    public UserEntity(Long id, String userId, String password, String userName) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.role = Role.user;
    }

    @Builder
    public void fixUserData(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }

    public void changeRole(){
        this.role = Role.admin;
    }
}
