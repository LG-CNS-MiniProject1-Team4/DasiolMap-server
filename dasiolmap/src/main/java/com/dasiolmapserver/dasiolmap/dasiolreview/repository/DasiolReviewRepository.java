package com.dasiolmapserver.dasiolmap.dasiolreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;

public interface DasiolReviewRepository extends JpaRepository<DasiolReviewEntity, Integer> {
    public List<DasiolReviewEntity> findByStore_StoreId(Integer storeId);

}