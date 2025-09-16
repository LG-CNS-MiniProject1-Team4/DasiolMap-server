package com.dasiolmapserver.dasiolmap.dasiolreview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewRequestDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.repository.DasiolReviewRepository;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class DasiolReviewService {
    @Autowired
    private DasiolReviewRepository reviewRepository;

    @Autowired
    private DasiolStoreRepository storeRepository;

    @Transactional
    public List<DasiolReviewResponseDTO> insert(@RequestBody @Valid DasiolReviewRequestDTO request) {
        System.out.println("[debug] >>> review service insert review ");
        DasiolStoreEntity store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("가게가 존재하지 않습니다. ID=" + request.getStoreId()));

        DasiolReviewEntity review = DasiolReviewEntity.builder()
                .review(request.getReview())
                .store(store)
                .build();
        store.getReviews().add(review);

        reviewRepository.save(review);

        List<DasiolReviewEntity> allReviews = reviewRepository.findByStore_StoreId(request.getStoreId());
        return allReviews.stream()
                .map(e -> DasiolReviewResponseDTO.fromEntity(e))
                .toList();
    }

    @Transactional
    public DasiolReviewEntity update(Integer reviewId, @RequestBody @Valid DasiolReviewRequestDTO request) {
        System.out.println("[debug] >>> review service update ");
        DasiolReviewEntity entity = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));
        if (request.getReview() != null) {
            entity.setReview(request.getReview());
        }
        if (request.getPhotos() != null) {
            entity.setPhotos(request.getPhotos());
        }

        return entity; // save() 호출 안 해도 @Transactional 이면 dirty checking으로 update 실행됨
    }

    public void delete(Integer storeId, Integer reviewId) {

        System.out.println("[debug] >>> review service delete review ");
        DasiolReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰가 존재하지 않습니다. ID=" + reviewId));

        if (!review.getStore().getStoreId().equals(storeId)) {
            throw new RuntimeException("잘못된 요청입니다.");
        }

        reviewRepository.delete(review);

    }

}
