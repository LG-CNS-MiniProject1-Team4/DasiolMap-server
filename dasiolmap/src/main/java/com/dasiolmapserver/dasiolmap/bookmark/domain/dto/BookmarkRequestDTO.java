package com.dasiolmapserver.dasiolmap.bookmark.domain.dto;

import com.dasiolmapserver.dasiolmap.bookmark.domain.entity.BookmarkEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

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
public class BookmarkRequestDTO {
    private Integer storeId;
    private Integer userId;

    public BookmarkEntity toEntity(DasiolStoreEntity store, UserEntity user) {
        return BookmarkEntity.builder()
                .store(store)
                .user(user)
                .build();
    }

}
