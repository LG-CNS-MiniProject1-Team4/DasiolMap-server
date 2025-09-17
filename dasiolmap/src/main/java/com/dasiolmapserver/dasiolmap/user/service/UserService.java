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
        System.out.println(">>> after save: "+entity);
        
        UserResponseDTO dto = UserResponseDTO.fromEntity(entity);
        System.out.println(">>> response dto: "+dto);

        return dto;
    }

    public Map<String, Object> login(UserRequestDTO request) {
        System.out.println(">>> service login");
        UserEntity entity = userRepository.findByEmailAndPasswdAndNickname(request.getEmail(), request.getPasswd(), request.getNickname());

        String accToken = provider.generateAccessToken(request.getEmail());
        String refToken = provider.generateRefreshToken(request.getEmail());
        UserResponseDTO response = UserResponseDTO.fromEntity(entity);

        Map<String, Object> map = new HashMap<>();
        map.put("response", response);
        map.put("access", accToken);
        map.put("refresh", refToken);
        return map;

    }

    public UserResponseDTO updateUser(String email, UserRequestDTO request) {
        System.out.println(">>> service updateUser");
        
        
        UserEntity user = userRepository.findByEmail(email);
        if( user == null ){
            throw new RuntimeException("User not found with email: "+ email);
        }
        if(request.getNickname() != null && request.getPasswd() != null) {
            user.setNickname(request.getNickname());
            user.setPasswd(request.getPasswd());
            System.out.println("update success/ nickname: "+ user.getNickname()+", passwd: "+ user.getPasswd());
        }                             
        

        UserEntity updated = userRepository.save(user);
        
        return UserResponseDTO.fromEntity(updated);
    }

    @Transactional
    public void deleteUser(Integer userId, String email) {
        UserEntity user = userRepository.findById(userId)
                    .orElseThrow( () -> new RuntimeException("사용자를 찾을 수 없습니다."));
        if(!user.getEmail().equals(email)){
            throw new RuntimeException("자신의 계정만 탈퇴할 수 있습니다.");
        }
        userRepository.delete(user);
    }
}
