package com.dasiolmapserver.dasiolmap.tag.domain.dto;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.tag.domain.entity.TagEntity;

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
public class TagRequestDTO {
    private Integer storeId;
    private String tagName;

    public TagEntity toEntity(DasiolStoreEntity store) {
        return TagEntity.builder()
                .store(store)
                .tagName(this.tagName)
                .build();
    }

}
