package com.dasiolmapserver.dasiolmap.bookmark.repository;

import org.springframework.stereotype.Repository;

import com.dasiolmapserver.dasiolmap.bookmark.domain.entity.BookmarkEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Integer> {
    public List<BookmarkEntity> findByUser_UserId(Integer userId);

    public List<BookmarkEntity> findByStore_StoreId(Integer storeId);

}