package com.dasiolmapserver.dasiolmap.storePhotos.domain.dto;

import com.dasiolmapserver.dasiolmap.storePhotos.domain.entity.StorePhotosEntity;
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
public class storePhotosResponseDTO {
    private Integer storePhotoID;
    private Integer storeId;
    private Integer reviewId;
    private String photoUrl;

    public static storePhotosResponseDTO fromEntity(StorePhotosEntity entity) {
        return storePhotosResponseDTO.builder()
                .storePhotoID(entity.getStorePhotoId())
                .storeId(entity.getStore().getStoreId())
                .reviewId(entity.getReview().getReviewId())
                .photoUrl(entity.getPhotoUrl())
                .build();
    }

}
