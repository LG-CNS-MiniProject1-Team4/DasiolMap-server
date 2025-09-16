package com.dasiolmapserver.dasiolmap.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findByEmailAndPasswd(String email, String passwd);

    public Optional<UserEntity> findByEmail(String email);

}
