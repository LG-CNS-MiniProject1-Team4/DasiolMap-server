package com.dasiolmapserver.dasiolmap.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public UserEntity findByEmailAndPasswdAndNickname(String email, String passwd, String nickname);
    public UserEntity findByEmail(String email);
}
