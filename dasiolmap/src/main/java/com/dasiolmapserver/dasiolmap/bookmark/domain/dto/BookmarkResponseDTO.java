package com.dasiolmapserver.dasiolmap.bookmark.domain.dto;

import com.dasiolmapserver.dasiolmap.bookmark.domain.entity.BookmarkEntity;

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
public class BookmarkResponseDTO {
    private Integer bookmarksId;
    private Integer storeId;
    private Integer userId;

    public static BookmarkResponseDTO fromEntity(BookmarkEntity bookmark) {
        return BookmarkResponseDTO.builder()
                .bookmarksId(bookmark.getBookmarkId())
                .storeId(bookmark.getStore().getStoreId())
                .userId(bookmark.getUser().getUserId())
                .build();
    }

}
