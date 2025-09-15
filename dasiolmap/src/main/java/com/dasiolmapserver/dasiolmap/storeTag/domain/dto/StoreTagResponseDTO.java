package com.dasiolmapserver.dasiolmap.storeTag.domain.dto;

import com.dasiolmapserver.dasiolmap.storeTag.domain.entity.StoreTagEntity;

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
public class StoreTagResponseDTO {

    private Integer storetagId;
    private String storeTagName;
    private Integer storeId;

    public static StoreTagResponseDTO fromEntity(StoreTagEntity storeTag) {
        return StoreTagResponseDTO.builder()
                .storetagId(storeTag.getStoreTagId())
                .storeTagName(storeTag.getStoreTagName())
                .storeId(storeTag.getStore().getStoreId())
                .build();
    }
}
