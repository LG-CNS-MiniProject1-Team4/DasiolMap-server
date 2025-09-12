package com.dasiolmapserver.dasiolmap.dasiolreview.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.service.DasiolReviewService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v2/dasiolmap/store/review")

public class DasiolReviewCtrl {

    @Autowired
    private DasiolReviewService reviewService;

    @PostMapping("/register")
    public ResponseEntity<List<DasiolReviewResponseDTO>> register(
            @RequestBody DasiolReviewRequsetDTO request) {

        System.out.println("[debug] >>> store comment ctrl path POST : /register ");
        System.out.println("[debug] param dto = " + request);

        List<DasiolReviewResponseDTO> comments = reviewService.insert(request);
        if (comments.size() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(comments);
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
