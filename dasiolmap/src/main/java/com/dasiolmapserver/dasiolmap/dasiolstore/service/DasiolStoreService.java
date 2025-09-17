package com.dasiolmapserver.dasiolmap.dasiolstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequestDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.AvgRatingMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DasiolStoreService {
    private final AvgRatingMapper avgRatingMapper;

    @Transactional
    public DasiolStoreRequestDTO addReview(int storeId, int rating, String review, int userId) {
        avgRatingMapper.insertReview(storeId, rating, review, userId);
        System.out.println(">>>> store service /addReview");

        return avgRatingMapper.findStoresWithAvgRating(storeId);
    }

    public DasiolStoreRequestDTO searchStore(int storeId) {

        System.out.println(">>>> store service /search");

        return avgRatingMapper.findStoresWithAvgRating(storeId);
    }

    
}
