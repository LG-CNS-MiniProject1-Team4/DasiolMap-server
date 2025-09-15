package com.dasiolmapserver.dasiolmap.dasiolreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;

@Repository
public interface DasiolReviewRepository extends JpaRepository<DasiolReviewEntity, Integer> {
    public List<DasiolReviewEntity> findByStore_StoreId(Integer storeId);

}