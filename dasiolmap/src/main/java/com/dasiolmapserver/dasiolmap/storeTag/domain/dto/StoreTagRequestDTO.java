package com.dasiolmapserver.dasiolmap.storeTag.domain.dto;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
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
public class StoreTagRequestDTO {
    private Integer storeId;
    private String storeTagName;

    public StoreTagEntity toEntity(DasiolStoreEntity store) {
        return StoreTagEntity.builder()
                .store(store)
                .storeTagName(this.storeTagName)
                .build();
    }

}
