package com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;

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
    private Integer storeId;

    public static DasiolReviewResponseDTO fromEntity(DasiolReviewEntity entity) {
        return DasiolReviewResponseDTO.builder()
                .reviewId(entity.getId())
                .riview(entity.getReview())
                .storeId(entity.getStore().getStoreId())
                .build();
    }

}
