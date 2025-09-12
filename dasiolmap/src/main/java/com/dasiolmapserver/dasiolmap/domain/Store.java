package com.dasiolmapserver.dasiolmap.domain;
// [Stores 테이블]

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String address;

    private String location;      // 위도, 경도 정보

    private Float avgRating;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder
    public Store(String storeName, String address, String location, Float avgRating) {
        this.storeName = storeName;
        this.address = address;
        this.location = location;
        this.avgRating = avgRating;
    }
    
}
