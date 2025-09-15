package com.dasiolmapserver.dasiolmap.dasiolstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;

@Repository
public interface DasiolStoreRepository extends JpaRepository<DasiolStoreEntity, Integer> {

    public List<DasiolStoreEntity> findByStoreTagId(Integer tagId);

    public List<DasiolStoreEntity> findByStoreStoreTagId(Integer storeTagId);

}