package com.dasiolmapserver.dasiolmap.domain;
// [Photos테이블]

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "photos")


public class Photo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(nullable = false)
    private String photoUrl;

    @Builder
    public Photo(Review review, String photoUrl) {
        this.review = review;
        this.photoUrl = photoUrl;
    }
}
