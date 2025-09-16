package com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto;

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
public class DasiolStoreRequestDTO {
    private String storeName;
    private String address;
    private String location;

    public DasiolStoreEntity toEntity() {
        return DasiolStoreEntity.builder()
                .storeName(this.storeName)
                .address(this.address)
                .location(this.location)
                .build();
    }
}