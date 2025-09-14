package com.dasiolmapserver.dasiolmap.user.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.user.domain.dto.UserResponseDTO;
import com.dasiolmapserver.dasiolmap.user.domain.dto.UserUpdateRequestDTO;
import com.dasiolmapserver.dasiolmap.user.repository.RefreshTokenRepository;
import com.dasiolmapserver.dasiolmap.user.service.UserService;
import com.dasiolmapserver.dasiolmap.util.JwtProvider;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
@RestController
@RequestMapping("/auth/api/v2/dasiolmap/user")
public class AuthUserCtrl {
    // JWT 필요 -> 로그아웃, 회원정보 수정
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateRequestDTO request,
                                                                                @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println(">>>> user ctrl PUT /updateUser");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);
        String email = jwtProvider.getEmailFromToken(token);

        UserResponseDTO response = userService.updateUser(email, request);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(value = "Authorization", required = false) String authHeader, HttpServletRequest request) {
        
        System.out.println(">>>> user ctrl POST /logout");
        System.out.println(">>>> Authorization: "+authHeader);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring(7);
        String email = jwtProvider.getEmailFromToken(token);
        refreshTokenRepository.delete(email);
        System.out.println(">>>> Refresh token deleted for user: " + email);
        return ResponseEntity.ok().build();
    }
    


    
}
