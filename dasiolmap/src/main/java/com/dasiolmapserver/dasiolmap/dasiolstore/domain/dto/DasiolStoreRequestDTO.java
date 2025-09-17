package com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DasiolStoreRequestDTO {

    private int storeId;
    private String storeName;
    private Double avgRating;

    public DasiolStoreEntity toEntity() {
        return DasiolStoreEntity.builder()
                        .storeName(storeName)
                        .build();
    }
}
