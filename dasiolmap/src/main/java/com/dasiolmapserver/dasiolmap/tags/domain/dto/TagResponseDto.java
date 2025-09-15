package com.dasiolmapserver.dasiolmap.tags.domain.dto;

import com.dasiolmapserver.dasiolmap.tags.domain.entity.Tag;
import lombok.Getter;

@Getter
public class TagResponseDto {
    private Long id;
    private String tagName;

    public TagResponseDto(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTagName();
    }
}
