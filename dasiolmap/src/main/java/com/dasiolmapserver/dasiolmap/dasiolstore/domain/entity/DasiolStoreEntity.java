package com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.entity.StorePhotoEntity;
import com.dasiolmapserver.dasiolmap.storeTag.domain.entity.StoreTagEntity;
import com.dasiolmapserver.dasiolmap.tag.domain.entity.TagEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    private Integer storeId;

    @Column(nullable = false, length = 10)
    private String storeName;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false, length = 150)
    private String location;

    @Column(nullable = true)
    private Float avgRating;

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DasiolReviewEntity> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<StorePhotoEntity> photos = new ArrayList<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tag", joinColumns = @JoinColumn(name = "storeId"), inverseJoinColumns = @JoinColumn(name = "tagId"))
    @JsonBackReference
    private Set<TagEntity> tags = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "storeTag", joinColumns = @JoinColumn(name = "storeId"), inverseJoinColumns = @JoinColumn(name = "storeTagId"))
    @JsonBackReference
    private Set<StoreTagEntity> storeTags = new HashSet<>();
}
