package com.dasiolmapserver.dasiolmap.storePhoto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.storePhoto.domain.entity.StorePhotoEntity;

@Repository
public interface StorePhotoRepository extends JpaRepository<StorePhotoEntity, Integer> {

    public List<StorePhotoEntity> findByStore_StoreId(Integer storeId);

    public List<StorePhotoEntity> findByUser_Email(String userEmail);

    public Optional<StorePhotoEntity> findByReview_ReviewId(Integer reviewId);

}
