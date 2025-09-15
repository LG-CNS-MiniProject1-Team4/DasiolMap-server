package com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto;

import java.util.List;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;

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

    private String storeId;
    private String storeName;
    private String address;
    private String location;

    // private List<DasiolReviewResponseDTO> reviews;
    public static DasiolStoreResponseDTO fromEntity(DasiolStoreEntity store) {
        return DasiolStoreResponseDTO.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .location(store.getLocation())
                // .reviews(
                // store.getReviews().stream()
                // .map(e -> DasiolReviewResponseDTO.fromEntity(e))
                // .toList())
                .build();
    }
}
