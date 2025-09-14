package com.dasiolmapserver.dasiolmap.dasiolstore.repository;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s FROM Store s JOIN s.tags t WHERE t.tagName IN :tagNames GROUP BY s HAVING COUNT(DISTINCT t) = :tagCount")
    List<Store> findStoresByTags(@Param("tagNames") List<String> tagNames, @Param("tagCount") Long tagCount);
}
