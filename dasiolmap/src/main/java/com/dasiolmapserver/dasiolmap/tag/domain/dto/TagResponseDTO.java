package com.dasiolmapserver.dasiolmap.tag.domain.dto;

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
public class TagResponseDTO {

    private Integer tagId;
    private String tagName;
    private Integer storeId;

    public static TagResponseDTO fromEntity(TagEntity tag) {
        return TagResponseDTO.builder()
                .tagId(tag.getTagId())
                .tagName(tag.getTagName())
                .storeId(tag.getStore().getStoreId())
                .build();
    }
}
