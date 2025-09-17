package com.dasiolmapserver.dasiolmap.dasiolstore.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequestDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.service.DasiolStoreService;
import com.dasiolmapserver.dasiolmap.user.repository.RefreshTokenRepository;
import com.dasiolmapserver.dasiolmap.user.service.UserService;
import com.dasiolmapserver.dasiolmap.util.JwtProvider;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/auth/api/v2/dasiolmap/store")
public class DasiolStoreCtrl {
    @Autowired
    private DasiolStoreService dasiolStoreService;
    
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<DasiolStoreRequestDTO> addReview(@PathVariable("storeId") Integer storeId, @RequestParam int rating,
                            @RequestParam String review, @RequestParam int userId, @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println(">>>> store ctrl /reviews");
                       
        DasiolStoreRequestDTO updatedStore = dasiolStoreService.addReview(storeId, rating, review, userId) ;  
        return ResponseEntity.ok(updatedStore);
    }
    
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/search")
    public ResponseEntity<DasiolStoreRequestDTO> search(@RequestParam Integer storeId, @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println(">>>> store ctrl /search");
                   
        DasiolStoreRequestDTO store = dasiolStoreService.searchStore(storeId);
        return ResponseEntity.ok(store);
    
    }
    
}
