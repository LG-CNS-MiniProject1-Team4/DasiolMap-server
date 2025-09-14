package com.dasiolmapserver.dasiolmap.user.domain.dto;

import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
public class UserRequestDTO {
    @Email(message = "이메일 형식과 맞지 않습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "비밀번호는 8자 이상이어야 하며, 영어 소문자와 숫자를 반드시 포함해야 합니다.")
    private String passwd;

    private String nickname;

    public UserEntity toEntity() {
        return UserEntity.builder()
                        .email(this.email)
                        .passwd(this.passwd)
                        .nickname(this.nickname)
                        .build();
    }
}

