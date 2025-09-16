package com.dasiolmapserver.dasiolmap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.dasiolmapserver.dasiolmap.user.domain.dto.UserRequestDTO;
import com.dasiolmapserver.dasiolmap.user.domain.dto.UserResponseDTO;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;
import com.dasiolmapserver.dasiolmap.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Commit
    public void insertUser() {
        // givn
        UserRequestDTO request = UserRequestDTO.builder()
                .email("jslim9413@naver.com")
                .passwd("1234")
                .nickname("임섭순")
                .build();
        // when
        UserEntity entity = userRepository.save(request.toEntity());

        UserResponseDTO response = UserResponseDTO.fromEntity(entity);

        // then
        System.out.println(">>>> entity   " + entity);
        System.out.println(">>>> response " + response);

    }
}
