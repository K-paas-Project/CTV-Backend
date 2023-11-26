package com.kpaas.ctv.kpaas.domain.auth.domain;

import com.kpaas.ctv.kpaas.global.common.constant.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userAccount;

    @Column
    private String password;

    @Column
    private String userName;

    @Column
    private String organization;

    @Column
    private Role role;

    @Builder
    public UserEntity(String userAccount, String password, String userName, String organization) {
        this.userAccount = userAccount;
        this.password = password;
        this.userName = userName;
        this.organization = organization;
        this.role = Role.user;
    }

    public void fixUserData(String userAccount, String userName, String organization) {
        this.userAccount = userAccount;
        this.userName = userName;
        this.organization = organization;
    }

    public void fixPassword(String password){
        this.password = password;
    }
    public void changeRole(){
        this.role = Role.admin;
    }
}
