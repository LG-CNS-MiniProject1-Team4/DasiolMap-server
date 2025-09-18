package com.dasiolmapserver.dasiolmap.dasiolstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;

@Repository
public interface DasiolStoreRepository extends JpaRepository<DasiolStoreEntity, Integer> {

    public List<DasiolStoreEntity> findByTags_TagId(Integer tagId);

    public List<DasiolStoreEntity> findByStoreTags_StoreTagId(Integer storeTagId);

    // 주소에 특정 문자열이 포함된 가게 검색
    List<DasiolStoreEntity> findByAddressContaining(String addressKeyword);

}