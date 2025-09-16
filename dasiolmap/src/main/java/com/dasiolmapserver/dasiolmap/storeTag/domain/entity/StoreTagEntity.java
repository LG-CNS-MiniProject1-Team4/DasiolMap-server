package com.dasiolmapserver.dasiolmap.storeTag.domain.entity;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;

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
public class StoreTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeTagId;

    @Column(nullable = false, unique = true)
    private String storeTagName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store")
    private DasiolStoreEntity store;
}
