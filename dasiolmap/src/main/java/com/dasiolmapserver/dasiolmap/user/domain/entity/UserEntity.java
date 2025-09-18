package com.dasiolmapserver.dasiolmap.user.domain.entity;

import org.hibernate.annotations.CreationTimestamp;

import com.dasiolmapserver.dasiolmap.bookmark.domain.entity.BookmarkEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    private String email;

    @Column(nullable = false, updatable = true)
    private String passwd;

    @Column(nullable = false, updatable = true)
    private String nickname;

    @CreationTimestamp
    @Column(updatable = false) // 수정 불가
    private LocalDateTime createdAt;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DasiolReviewEntity> reviews = new ArrayList<>();

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookmarkEntity> bookmarks = new ArrayList<>();
}