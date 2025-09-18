package com.dasiolmapserver.dasiolmap.storePhoto.domain.entity;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class StorePhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storePhotoId; // 사진 PK

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store")
    @JsonBackReference
    private DasiolStoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user")
    @JsonBackReference
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review")
    @JsonBackReference
    private DasiolReviewEntity review;

    @Column(nullable = false, length = 1000)
    private String photoUrl; //

}
