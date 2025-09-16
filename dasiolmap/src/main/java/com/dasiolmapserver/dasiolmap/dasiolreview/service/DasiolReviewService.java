package com.dasiolmapserver.dasiolmap.dasiolreview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.repository.DasiolReviewRepository;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;
import com.dasiolmapserver.dasiolmap.user.repository.UserRepository;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreSearchRepository;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.document.DasiolStoreDocument;

import jakarta.transaction.Transactional;

@Service
public class DasiolReviewService {
    @Autowired
    private DasiolReviewRepository reviewRepository;

    @Autowired
    private DasiolStoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DasiolStoreSearchRepository dasiolStoreSearchRepository;

    @Transactional
    public List<DasiolReviewResponseDTO> insert(DasiolReviewRequsetDTO request) {
        System.out.println("[debug] >>> review service insert review ");
        DasiolStoreEntity store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("가게가 존재하지 않습니다. ID=" + request.getStoreId()));

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다. ID=" + request.getUserId()));

        DasiolReviewEntity review = request.toEntity(store, user); // 👈 toEntity 호출 방식 변경

        store.getReviews().add(review);
        user.getReviews().add(review);

        reviewRepository.save(review);

         // 1. 현재 가게의 모든 리뷰를 가져옵니다.
        List<DasiolReviewEntity> allReviews = reviewRepository.findByStoreStoreId(request.getStoreId());
        
        // 2. 모든 리뷰의 평점(rating)의 평균을 계산합니다. (리뷰가 없으면 0.0)
        double averageRating = allReviews.stream()
                                        .mapToInt(DasiolReviewEntity::getRating)
                                        .average()
                                        .orElse(0.0);
                                        
        // 3. 계산된 평균을 가게(DasiolStoreEntity)의 avgRating 필드에 업데이트합니다.
        store.setAvgRating((float) averageRating);
        storeRepository.save(store); // 변경된 가게 정보 DB에 저장
        
        // 4. Elasticsearch 데이터도 업데이트하여 검색 결과에 즉시 반영합니다.
        DasiolStoreDocument document = dasiolStoreSearchRepository.findById(store.getStoreId()).orElse(new DasiolStoreDocument());
        document.setStoreId(store.getStoreId());
        document.setStoreName(store.getStoreName());
        document.setAddress(store.getAddress());
        document.setLocation(store.getLocation());
        document.setAvgRating(store.getAvgRating());
        dasiolStoreSearchRepository.save(document);

        return allReviews.stream()
                .map(DasiolReviewResponseDTO::fromEntity)
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
