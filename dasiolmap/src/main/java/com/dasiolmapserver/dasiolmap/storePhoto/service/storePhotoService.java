package com.dasiolmapserver.dasiolmap.storePhoto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.repository.DasiolReviewRepository;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.dto.StorePhotoRequestDTO;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.dto.StorePhotoResponseDTO;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.entity.StorePhotoEntity;
import com.dasiolmapserver.dasiolmap.storePhoto.repository.StorePhotoRepository;

@Service
public class StorePhotoService {
        @Autowired
        private DasiolReviewRepository dasiolReviewRepository;

        @Autowired
        private DasiolStoreRepository dasiolStoreRepository;

        @Autowired
        private StorePhotoRepository StorePhotoRepository;

        public List<StorePhotoResponseDTO> insert(StorePhotoRequestDTO request) {
                System.out.println("[debug] >>> storePhoto service insert ");

                // 스토어 엔티티 조회
                DasiolStoreEntity store = dasiolStoreRepository.findById(request.getStoreId())
                                .orElseThrow(() -> new RuntimeException("스토어 없음"));

                // 리뷰 엔티티 조회
                DasiolReviewEntity review = dasiolReviewRepository.findById(request.getReviewId())
                                .orElseThrow(() -> new RuntimeException("리뷰 없음"));

                StorePhotoEntity photo = StorePhotoEntity.builder()
                                .store(store)
                                .review(review)
                                .photoUrl(request.getPhotoUrl())
                                .build();
                review.getPhotos().add(photo);
                StorePhotoRepository.save(photo);

                Optional<StorePhotoEntity> allPhotos = StorePhotoRepository
                                .findByReview_ReviewId(request.getReviewId());
                return allPhotos.stream()
                                .map(e -> StorePhotoResponseDTO.fromEntity(e))
                                .toList();
        }

        public void delete(Integer reviewId, Integer storePhotoId) {

                System.out.println("[debug] >>> storePhoto service delete storePhoto ");
                StorePhotoEntity photo = StorePhotoRepository.findById(storePhotoId)
                                .orElseThrow(() -> new RuntimeException("사진이 존재하지 않습니다. ID=" + storePhotoId));

                if (!photo.getReview().getReviewId().equals(reviewId)) {
                        throw new RuntimeException("잘못된 요청입니다.");
                }

                StorePhotoRepository.delete(photo);

        }
}
