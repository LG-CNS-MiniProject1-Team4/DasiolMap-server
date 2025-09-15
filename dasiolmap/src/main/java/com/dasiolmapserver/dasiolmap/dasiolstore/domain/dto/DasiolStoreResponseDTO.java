package com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto;

import java.util.List;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.dto.StorePhotoResponseDTO;
import com.dasiolmapserver.dasiolmap.storeTag.domain.dto.StoreTagResponseDTO;
import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagResponseDTO;

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
public class DasiolStoreResponseDTO {

        private Integer storeId;
        private String storeName;
        private String address;
        private String location;
        private Float avgRating;

        private List<DasiolReviewResponseDTO> reviews;
        private List<StorePhotoResponseDTO> photos;
        private List<TagResponseDTO> tags;
        private List<StoreTagResponseDTO> storeTags;

        public static DasiolStoreResponseDTO fromEntity(DasiolStoreEntity store) {
                return DasiolStoreResponseDTO.builder()
                                .storeId(store.getStoreId())
                                .storeName(store.getStoreName())
                                .address(store.getAddress())
                                .location(store.getLocation())
                                .avgRating(store.getAvgRating())
                                .reviews(store.getReviews().stream()
                                                .map(e -> DasiolReviewResponseDTO.fromEntity(e))
                                                .toList())
                                .photos(store.getPhotos().stream()
                                                .map(e -> StorePhotoResponseDTO.fromEntity(e))
                                                .toList())
                                .storeTags(store.getStoreTags().stream()
                                                .map(e -> StoreTagResponseDTO.fromEntity(e))
                                                .toList())
                                .tags(store.getTags().stream()
                                                .map(e -> TagResponseDTO.fromEntity(e))
                                                .toList())
                                .build();
        }
}
