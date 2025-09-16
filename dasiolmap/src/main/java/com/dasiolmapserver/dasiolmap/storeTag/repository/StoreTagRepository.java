package com.dasiolmapserver.dasiolmap.storeTag.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasiolmapserver.dasiolmap.storeTag.domain.entity.StoreTagEntity;

@Repository
public interface StoreTagRepository extends JpaRepository<StoreTagEntity, Integer> {

    public StoreTagEntity findByStoreTagName(String storeTagName);

    public Optional<StoreTagEntity> findByStore_StoreId(Integer storeId);

}
