package com.dasiolmapserver.dasiolmap.openai.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.openai.domain.dto.ChatResponseDTO;
import com.dasiolmapserver.dasiolmap.openai.service.ChatService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/auth/api/v2/dasiolmap/ai")
public class ChatCtrl {

    @Autowired
    private ChatService chatService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/chat")
    public ResponseEntity<ChatResponseDTO> chat(
            @RequestParam(name = "userTags") List<String> userTags,
            @RequestParam(name = "storeName") String storeName) {
        System.out.println(">>>>> chat ctrl path POST");
        System.out.println(">>>>> prompt " + userTags);
        System.out.println(">>>>> prompt " + storeName);

        ChatResponseDTO result = chatService.recommendStore(userTags, storeName);
        /////////////

        return ResponseEntity.ok().body(result);
    }
}
