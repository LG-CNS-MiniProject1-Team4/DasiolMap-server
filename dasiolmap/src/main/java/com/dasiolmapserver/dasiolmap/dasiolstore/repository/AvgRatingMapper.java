package com.dasiolmapserver.dasiolmap.dasiolstore.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequestDTO;


@Mapper
public interface AvgRatingMapper {
    public DasiolStoreRequestDTO findStoresWithAvgRating(@Param("storeId") int storeId);
    public void insertReview(@Param("storeId") Integer storeId, @Param("rating") int rating, @Param("review") String review, @Param("userId") Integer userId);
}
