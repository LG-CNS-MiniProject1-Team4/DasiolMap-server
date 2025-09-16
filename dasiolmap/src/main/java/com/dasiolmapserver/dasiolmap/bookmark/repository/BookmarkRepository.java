package com.dasiolmapserver.dasiolmap.bookmark.repository;

import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.bookmark.domain.entity.BookmarkEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Integer> {
    public Optional<BookmarkEntity> findByUser_Email(String userEmail);

    public Optional<BookmarkEntity> findByStore_StoreId(Integer storeId);

    public Optional<BookmarkEntity> findByUserAndStore(UserEntity user, DasiolStoreEntity store);

}