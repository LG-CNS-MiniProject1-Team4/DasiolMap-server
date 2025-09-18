package com.dasiolmapserver.dasiolmap.user.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.dasiolmapserver.dasiolmap.bookmark.domain.entity.BookmarkEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;

import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {

    private String email;
    private String passwd;
    private String nickname;
    private LocalDateTime createdAt;

    //private List<DasiolReviewEntity> reviews;
    private List<DasiolReviewResponseDTO> reviews;   //DTO 타입으로 변경
    private List<BookmarkEntity> bookmarks;

    public static UserResponseDTO fromEntity(UserEntity user) {
        return UserResponseDTO.builder()
                .email(user.getEmail())
                .passwd(user.getPasswd())
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                //.reviewㄴ(user.getReviews())
                .reviews(user.getReviews().stream()
                            .map(DasiolReviewResponseDTO::fromEntity)
                            .toList())
                .bookmarks(user.getBookmarks())
                .build();
    }
}
