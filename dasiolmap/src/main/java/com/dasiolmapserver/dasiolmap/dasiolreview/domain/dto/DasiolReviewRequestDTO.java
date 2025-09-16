package com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto;

import java.util.List;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
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
public class DasiolReviewRequestDTO {
    private String review;
    private float rating;
    private Integer storeId;

    private List<StorePhotoEntity> photos;

    public DasiolReviewEntity toEntity(DasiolStoreEntity store) {
        return DasiolReviewEntity.builder()
                .review(this.review)
                .rating(this.rating)
                .store(store)
                .photos(this.photos)
                .build();
    }

}
