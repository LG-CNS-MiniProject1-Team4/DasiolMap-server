package com.dasiolmapserver.dasiolmap.dasiolstore.service;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.Store;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    
    private final StoreRepository storeRepository;

    public List<Store> findStoresByTags(List<String> tagNames) {
        if (tagNames == null || tagNames.isEmpty()) {
            return storeRepository.findAll(); // 태그가 없으면 전체 목록 반환
        }
        return storeRepository.findStoresByTags(tagNames, (long) tagNames.size());
    }
}
