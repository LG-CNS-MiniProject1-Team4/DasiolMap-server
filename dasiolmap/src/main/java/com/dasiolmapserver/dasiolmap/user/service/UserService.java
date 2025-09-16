package com.dasiolmapserver.dasiolmap.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.user.domain.dto.UserRequestDTO;
import com.dasiolmapserver.dasiolmap.user.domain.dto.UserResponseDTO;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;
import com.dasiolmapserver.dasiolmap.user.repository.UserRepository;
import com.dasiolmapserver.dasiolmap.util.JwtProvider;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider provider;

    public UserResponseDTO signup(UserRequestDTO request) {
        System.out.println(">>> service signup");
        UserEntity entity = userRepository.save(request.toEntity());
        System.out.println(">>> after save: " + entity);

        UserResponseDTO dto = UserResponseDTO.fromEntity(entity);
        System.out.println(">>> response dto: " + dto);

        return dto;
    }

    public Map<String, Object> login(UserRequestDTO request) {
        System.out.println(">>> service login");
        UserEntity entity = userRepository.findByEmailAndPasswd(request.getEmail(), request.getPasswd());

        String accToken = provider.generateAccessToken(request.getEmail());
        String refToken = provider.generateRefreshToken(request.getEmail());
        UserResponseDTO response = UserResponseDTO.fromEntity(entity);

        Map<String, Object> map = new HashMap<>();
        map.put("response", response);
        map.put("access", accToken);
        map.put("refresh", refToken);
        return map;

    }

    @Transactional
    public UserEntity update(String userEmail, UserRequestDTO request) {
        System.out.println("[debug] >>> store service update ");
        UserEntity entity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        if (request.getNickname() != null) {
            entity.setNickname(request.getNickname());
        }
        if (request.getPasswd() != null) {
            entity.setPasswd(request.getPasswd());
        }
        return entity;
    }
}
