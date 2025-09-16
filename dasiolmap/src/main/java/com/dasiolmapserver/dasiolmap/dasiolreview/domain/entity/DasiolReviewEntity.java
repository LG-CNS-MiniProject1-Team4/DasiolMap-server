package com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.entity.StorePhotoEntity;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class DasiolReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(nullable = false, length = 500)
    private String review;

    @Column(nullable = false)
    private float rating;   //1점에서 5점 사이로

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store")
    @JsonBackReference
    private DasiolStoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    @JsonBackReference
    private UserEntity user;

    @Builder.Default
    @JsonBackReference
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StorePhotoEntity> photos = new ArrayList<>();
}
