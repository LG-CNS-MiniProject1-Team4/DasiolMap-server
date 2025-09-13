package com.dasiolmapserver.dasiolmap.user.domain.dto;
// [클라이언트가 로그인을 요청할 때 보내는 데이터]

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    private String email;
    private String password;
}
