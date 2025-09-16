package com.dasiolmapserver.dasiolmap.storeTag.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.storeTag.domain.dto.StoreTagRequestDTO;
import com.dasiolmapserver.dasiolmap.storeTag.domain.dto.StoreTagResponseDTO;
import com.dasiolmapserver.dasiolmap.storeTag.service.StoreTagService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/auth/api/v2/dasiolmap/store/storetag")
public class storeTagCtrl {
    @Autowired
    private StoreTagService storeTagService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<List<StoreTagResponseDTO>> register(
            @RequestBody StoreTagRequestDTO request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        System.out.println("[debug] >>> store review photo ctrl path POST : /register ");
        System.out.println("[debug] param dto = " + request);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<StoreTagResponseDTO> tags = storeTagService.insert(request);
        if (tags.size() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tags);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/delete/{storeId}/{storeTagId}")
    public ResponseEntity<Void> delete(
            @PathVariable("storeId") Integer storeId,
            @PathVariable("storeTagId") Integer storeTagId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println("[debug] >>> Store review ctrl path DELETE : /delete ");
        System.out.println("[debug] param storeId = " + storeId);
        System.out.println("[debug] param reviewId = " + storeTagId);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        storeTagService.delete(storeId, storeTagId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
