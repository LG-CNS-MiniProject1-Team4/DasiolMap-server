package com.dasiolmapserver.dasiolmap.dasiolreview.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.service.DasiolReviewService;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v2/dasiolmap/store/review")
public class DasiolReviewCtrl {

    @Autowired
    private DasiolReviewService reviewService;

    @PostMapping("/register")
    public ResponseEntity<List<DasiolReviewResponseDTO>> register(
            @RequestBody DasiolReviewRequsetDTO request) {

        System.out.println("[debug] >>> store review ctrl path POST : /register ");
        System.out.println("[debug] param dto = " + request);

        List<DasiolReviewResponseDTO> reviews = reviewService.insert(request);
        if (reviews.size() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(reviews);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{storeId}/{reviewId}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Integer reviewId,
            @org.springframework.web.bind.annotation.RequestBody DasiolReviewRequsetDTO request) {

        System.out.println("[debug] >>> review ctrl path PUT : /update ");
        System.out.println("[debug] >>> param is = " + reviewId);
        System.out.println("[debug] >>> param dto = " + request);

        DasiolReviewEntity result = reviewService.update(reviewId, request);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{storeId}/{reviewId}")
    public ResponseEntity<Void> delete(
            @PathVariable("storeId") Integer storeId,
            @PathVariable("reviewId") Integer reviewId) {
        System.out.println("[debug] >>> Store review ctrl path DELETE : /delete ");
        System.out.println("[debug] param storeId = " + storeId);
        System.out.println("[debug] param reviewId = " + reviewId);
        reviewService.delete(storeId, reviewId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
