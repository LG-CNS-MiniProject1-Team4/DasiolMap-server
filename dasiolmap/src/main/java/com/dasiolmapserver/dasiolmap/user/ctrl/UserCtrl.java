package com.dasiolmapserver.dasiolmap.user.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.user.domain.dto.UserRequestDTO;
import com.dasiolmapserver.dasiolmap.user.domain.dto.UserResponseDTO;
import com.dasiolmapserver.dasiolmap.user.service.UserService;
import com.dasiolmapserver.dasiolmap.util.JwtProvider;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v2/dasiolmap/user")
public class UserCtrl {
    
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserRequestDTO request, BindingResult bindingResult) {
        System.out.println(">>>> user ctrl POST /signup");
        System.out.println(">>>> user ctrl POST /signup param : "+request);
        
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getAllErrors().forEach(err -> {
                FieldError filed = (FieldError) err;
                String msg = err.getDefaultMessage();

                System.out.println("[debug] >>> validation err: "+filed.getField()+" - "+msg);
                errorMap.put(filed.getField(), msg);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }

        UserResponseDTO response = userService.signup(request);
        if(response != null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO request) {
        System.out.println(">>>> user ctrl POST /signin");
        System.out.println(">>>> user ctrl POST /signin param: "+request);
        Map<String, Object> map = userService.login(request);

        return ResponseEntity.status(HttpStatus.OK)
                                .header("Authorization", "Bearer "+(String)map.get("access"))
                                .header("Refresh-Token", (String)(map.get("refresh")))
                                .body((UserResponseDTO)map.get("response"));
    }
     
}
