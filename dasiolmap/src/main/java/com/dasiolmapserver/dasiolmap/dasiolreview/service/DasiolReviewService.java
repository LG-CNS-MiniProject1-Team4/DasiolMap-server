package com.dasiolmapserver.dasiolmap.dasiolreview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.repository.DasiolReviewRepository;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;

import jakarta.transaction.Transactional;

@Service
public class DasiolReviewService {
    @Autowired
    private DasiolReviewRepository reviewRepository;

    @Autowired
    private DasiolStoreRepository storeRepository;

    @Transactional
    public List<DasiolReviewResponseDTO> insert(DasiolReviewRequsetDTO request) {
        System.out.println("[debug] >>> comment service insert comment ");
        DasiolStoreEntity store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("블로그가 존재하지 않습니다. ID=" + request.getStoreId()));

        DasiolReviewEntity review = DasiolReviewEntity.builder()
                .review(request.getReview())
                .store(store)
                .build();
        store.getReviews().add(review);

        reviewRepository.save(review);

        List<DasiolReviewEntity> allComments = reviewRepository.findByStore_StoreId(request.getStoreId());
        return allComments.stream()
                .map(e -> DasiolReviewResponseDTO.fromEntity(e))
                .toList();

    }

    @Transactional
    public DasiolReviewEntity update(Integer reviewId, DasiolReviewRequsetDTO request) {
        System.out.println("[debug] >>> review service update ");
        DasiolReviewEntity entity = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));
        entity.setReview(request.getReview());

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
