package com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto;

import java.util.List;

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
public class DasiolReviewResponseDTO {
    private Integer reviewId;
    private String riview;
    private float rating;
    private Integer storeId;
    private String userEmail;

    private List<StorePhotoEntity> photos;

    public static DasiolReviewResponseDTO fromEntity(DasiolReviewEntity entity) {
        return DasiolReviewResponseDTO.builder()
                .reviewId(entity.getReviewId())
                .riview(entity.getReview())
                .rating(entity.getRating())
                .storeId(entity.getStore().getStoreId())
                .userEmail(entity.getUser().getEmail())
                .photos(entity.getPhotos())
                .build();
    }

}
