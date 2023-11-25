package com.kpaas.ctv.kpaas.domain.auth.domain.repository;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
