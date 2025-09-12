package com.dasiolmapserver.dasiolmap.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reviews")

public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private Integer rating;

    @Lob // 텍스트를 많이 저장할수 있는 타입
    private String content;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    @Builder
    public Review(User user, Store store, Integer rating, String content) {
        this.user = user;
        this.store = store;
        this.rating = rating;
        this.content = content;
    }
    
}
