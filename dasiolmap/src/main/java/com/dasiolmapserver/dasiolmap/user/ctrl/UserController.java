package com.dasiolmapserver.dasiolmap.user.ctrl;

import com.dasiolmapserver.dasiolmap.user.domain.dto.UserSignUpRequestDto;
import com.dasiolmapserver.dasiolmap.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     // 이 클래스가 API 요청을 처리하는 컨트롤러임을 나타냅니다
@RequiredArgsConstructor
@RequestMapping("/api/user")    // 이 컨트롤러의 모든 API는 /api/user 라는 주소로 시작됩니다.
public class UserController {
    
    private final UserService userService;

    @PostMapping("/signup")     // HTTP POST 요청과 /signup 주소를 합쳐, POST /api/user/signup API를 만듭니다.
    public ResponseEntity<String> signUp(@RequestBody UserSignUpRequestDto requestDto) {
        userService.signUp(requestDto);
        return ResponseEntity.ok("Sign-up successful!");
    }
}
