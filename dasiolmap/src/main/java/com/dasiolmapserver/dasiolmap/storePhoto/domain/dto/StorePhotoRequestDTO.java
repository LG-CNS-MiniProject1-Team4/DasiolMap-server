package com.dasiolmapserver.dasiolmap.storePhoto.domain.dto;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.entity.StorePhotoEntity;

public class StorePhotoRequestDTO {
    private Integer storeId;
    private Integer reviewId;
    private String photoUrl;

    public StorePhotoEntity toEntity(DasiolReviewEntity review) {
        return StorePhotoEntity.builder()
                .store(review.getStore())
                .review(review)
                .photoUrl(this.photoUrl)
                .build();
    }
}
