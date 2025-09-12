package com.dasiolmapserver.dasiolmap.domain;
// [Bookmarks 테이블]

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "bookmarks")

public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ERD에는 pk가 없지만, JPA에서는 필수입니다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder
    public Bookmark(User user, Store store) {
        this.user = user;
        this.store = store;
    }
}
