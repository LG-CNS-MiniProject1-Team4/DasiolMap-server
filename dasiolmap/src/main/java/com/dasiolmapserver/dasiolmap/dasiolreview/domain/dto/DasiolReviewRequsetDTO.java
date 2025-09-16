package com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

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
public class DasiolReviewRequsetDTO {
    private String review;
    private Integer rating; //  Postman의 rating 값을 받습니다.
    private Integer storeId;
    private Integer userId;

    public DasiolReviewEntity toEntity(DasiolStoreEntity store, UserEntity user) {
        return DasiolReviewEntity.builder()
                .review(this.review)
                .rating(this.rating) // 받은 rating 값을 Entity로 넘겨줍니다.
                .store(store)
                .user(user)
                .build();
    }

}