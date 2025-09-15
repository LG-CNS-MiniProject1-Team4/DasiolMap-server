package com.dasiolmapserver.dasiolmap.dasiolstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;

@Repository
public interface DasiolStoreRepository extends JpaRepository<DasiolStoreEntity, Integer> {

    @Query("SELECT s FROM DasiolStoreEntity s JOIN s.tags t WHERE t.tagId = :tagId")
    public List<DasiolStoreEntity> findByTagId(@Param("tagId") Integer tagId);

    @Query("SELECT s FROM DasiolStoreEntity s JOIN s.storeTags st WHERE st.storeTagId = :storeTagId")
    public List<DasiolStoreEntity> findByStoreTagId(@Param("storeTagId") Integer storeTagId);

}