package com.dasiolmapserver.dasiolmap.user.domain.dto;

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

    private String email, passwd, nickname ;

    public static UserResponseDTO fromEntity(UserEntity user) {
        return UserResponseDTO.builder()
                                .email(user.getEmail())
                                .passwd(user.getPasswd())
                                .nickname(user.getNickname())
                                .build();
                    
    }
}
