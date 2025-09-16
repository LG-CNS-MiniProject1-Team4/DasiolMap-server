package com.dasiolmapserver.dasiolmap.storePhoto.ctrl;

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
import org.springframework.web.bind.annotation.RequestBody;

import com.dasiolmapserver.dasiolmap.storePhoto.domain.dto.StorePhotoRequestDTO;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.dto.StorePhotoResponseDTO;

import com.dasiolmapserver.dasiolmap.storePhoto.service.StorePhotoService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/auth/api/v2/dasiolmap/store/review/photo")
public class StorePhotoCtrl {
    @Autowired
    private StorePhotoService storePhotoService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<List<StorePhotoResponseDTO>> register(
            @RequestBody StorePhotoRequestDTO request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        System.out.println("[debug] >>> store review photo ctrl path POST : /register ");
        System.out.println("[debug] param dto = " + request);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<StorePhotoResponseDTO> Photos = storePhotoService.insert(request);
        if (Photos.size() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Photos);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/delete/{storeId}/{reviewId}/{photoId}")
    public ResponseEntity<Void> delete(
            @PathVariable("reviewId") Integer reviewId,
            @PathVariable("storePhotoId") Integer storePhotoId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println("[debug] >>> Store review ctrl path DELETE : /delete ");
        System.out.println("[debug] param storeId = " + reviewId);
        System.out.println("[debug] param reviewId = " + storePhotoId);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        storePhotoService.delete(reviewId, storePhotoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
