package com.dasiolmapserver.dasiolmap.tag.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.tag.domain.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {
    public TagEntity findByTagName(String tagName);

    public Optional<TagEntity> findByStore_StoreId(Integer storeId);
}
