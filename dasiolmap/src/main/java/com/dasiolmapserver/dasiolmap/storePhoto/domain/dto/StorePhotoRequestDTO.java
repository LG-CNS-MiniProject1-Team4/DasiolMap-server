package com.dasiolmapserver.dasiolmap.storePhoto.domain.dto;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.entity.StorePhotoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
