package com.dasiolmapserver.dasiolmap.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasiolmapserver.dasiolmap.tag.domain.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
    
}
