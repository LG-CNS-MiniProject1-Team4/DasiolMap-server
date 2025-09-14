package com.dasiolmapserver.dasiolmap.bookmarks.domain.dto;

import com.dasiolmapserver.dasiolmap.bookmarks.domain.entity.BookmarksEntity;
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
public class BookmarksRequestDTO {
    private Integer storeId;

    public BookmarksEntity toEntity(DasiolStoreEntity store) {
        return BookmarksEntity.builder()
                .store(store)
                .build();
    }

}
