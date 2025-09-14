package com.dasiolmapserver.dasiolmap.bookmarks.domain.dto;

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
public class BookmarksResponseDTO {
    private Integer bookmarksId;
    private Integer storeId;
    private Integer userId;

    public static BookmarksResponseDTO fromEntity(BookmarksResponseDTO entity) {
        return BookmarksResponseDTO.builder()
                .bookmarksId(entity.getBookmarksId())
                .storeId(entity.getStoreId())
                .userId(entity.getUserId())
                .build();
    }

}
