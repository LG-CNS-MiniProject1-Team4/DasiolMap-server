package com.dasiolmapserver.dasiolmap.user.domain.dto;

import java.time.LocalDateTime;

import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {

    private Integer userId;
    private String email;
    private String passwd;
    private String nickname;
    private LocalDateTime createdAt;

    public static UserResponseDTO fromEntity(UserEntity user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .passwd(user.getPasswd())
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                .build();

    }
}
