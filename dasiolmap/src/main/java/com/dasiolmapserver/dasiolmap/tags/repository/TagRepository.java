package com.dasiolmapserver.dasiolmap.tags.repository;

import com.dasiolmapserver.dasiolmap.tags.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>{
    
}
