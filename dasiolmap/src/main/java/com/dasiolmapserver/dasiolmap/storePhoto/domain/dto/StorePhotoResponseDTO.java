package com.dasiolmapserver.dasiolmap.storePhoto.domain.dto;

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
public class StorePhotoResponseDTO {
    private Integer storePhotoID;
    private Integer storeId;
    private Integer reviewId;
    private String photoUrl;

    public static StorePhotoResponseDTO fromEntity(StorePhotoEntity entity) {
        return StorePhotoResponseDTO.builder()
                .storePhotoID(entity.getStorePhotoId())
                .storeId(entity.getStore().getStoreId())
                .reviewId(entity.getReview().getReviewId())
                .photoUrl(entity.getPhotoUrl())
                .build();
    }

}
