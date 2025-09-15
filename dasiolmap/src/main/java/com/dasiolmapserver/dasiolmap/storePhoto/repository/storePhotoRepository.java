package com.dasiolmapserver.dasiolmap.storePhoto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.storePhoto.domain.entity.StorePhotoEntity;

@Repository
public interface storePhotoRepository extends JpaRepository<StorePhotoEntity, Integer> {

    public List<StorePhotoEntity> findByStoreStoreId(Integer storeId);

    public List<StorePhotoEntity> findByUserUserId(Integer userId);

}
