package com.dasiolmapserver.dasiolmap.dasiolstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.DasiolStore.domain.entity.DasiolStoreEntity;

@Repository
public interface DasiolStoreRepository extends JpaRepository<DasiolStoreEntity, String> {

}