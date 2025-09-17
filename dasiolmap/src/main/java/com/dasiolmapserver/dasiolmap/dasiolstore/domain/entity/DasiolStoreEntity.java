package com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.dasiolmapserver.dasiolmap.bookmarks.domain.entity.BookmarksEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DasiolStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;

    @Column(nullable = false, length = 10)
    private String storeName;

    // 평균 별점
    // @Column(nullable = true)
    // private Double avgRating;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false, length = 150)
    private String location;

    // 외래키 설정
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DasiolReviewEntity> reviews = new ArrayList<>();

}