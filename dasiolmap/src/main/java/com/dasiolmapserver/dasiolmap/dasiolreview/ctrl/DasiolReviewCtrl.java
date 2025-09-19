package com.dasiolmapserver.dasiolmap.dasiolreview.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewRequestDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.service.DasiolReviewService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/auth/api/v2/dasiolmap/store/review")
public class DasiolReviewCtrl {

    @Autowired
    private DasiolReviewService reviewService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")

    public ResponseEntity<List<DasiolReviewResponseDTO>> register(
        @RequestBody Map<String, Object> map,    
        @RequestHeader(value = "Authorization", required = false) String authHeader) {

        System.out.println("[debug] >>> store review ctrl path POST : /register ");
        System.out.println("[debug] param dto = " + map.get("review") );
        DasiolReviewRequestDTO request = DasiolReviewRequestDTO.builder()
                                            .review((String)(map.get("review")))
                                            .rating((Integer)(map.get("rating")))
                                            .storeId((Integer)(map.get("storeId")))
                                            .userEmail((String)(map.get("userEmail")))
                                            .build() ; 
         System.out.println(">>>>>> request " + request );    
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<DasiolReviewResponseDTO> reviews = reviewService.insert(request);
        if (reviews.size() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(reviews);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/update/{storeId}/{reviewId}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Integer reviewId,
            @org.springframework.web.bind.annotation.RequestBody DasiolReviewRequestDTO request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        System.out.println("[debug] >>> review ctrl path PUT : /update ");
        System.out.println("[debug] >>> param is = " + reviewId);
        System.out.println("[debug] >>> param dto = " + request);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        DasiolReviewEntity result = reviewService.update(reviewId, request);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/delete/{storeId}/{reviewId}")
    public ResponseEntity<Void> delete(
            @PathVariable("storeId") Integer storeId,
            @PathVariable("reviewId") Integer reviewId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println("[debug] >>> Store review ctrl path DELETE : /delete ");
        System.out.println("[debug] param storeId = " + storeId);
        System.out.println("[debug] param reviewId = " + reviewId);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        reviewService.delete(storeId, reviewId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}