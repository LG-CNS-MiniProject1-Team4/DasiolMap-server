package com.dasiolmapserver.dasiolmap.user.domain.dto;
// [클라이언트가 회원가입을 요청할 때 보내는 데이터 (email, password, nickname)를 담는 역활]

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String nickname;
}
