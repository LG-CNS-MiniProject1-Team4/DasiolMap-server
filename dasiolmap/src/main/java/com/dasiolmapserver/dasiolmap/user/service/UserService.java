package com.dasiolmapserver.dasiolmap.user.service;

import com.dasiolmapserver.dasiolmap.user.domain.dto.UserSignUpRequestDto;
import com.dasiolmapserver.dasiolmap.user.domain.entity.User;
import com.dasiolmapserver.dasiolmap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(UserSignUpRequestDto requestDto) {
        // 1. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        // 2. DTO를 Entity로 변환하여 User 객체 생성
        User user = User.builder()
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .nickname(requestDto.getNickname())
                .build();

        // 3. Repository를 통해 DB에 저장
        userRepository.save(user);
    }
}
