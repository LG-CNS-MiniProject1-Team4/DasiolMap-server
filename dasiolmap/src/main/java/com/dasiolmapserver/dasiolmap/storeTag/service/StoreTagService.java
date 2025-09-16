package com.dasiolmapserver.dasiolmap.storeTag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.dto.StorePhotoResponseDTO;
import com.dasiolmapserver.dasiolmap.storePhoto.domain.entity.StorePhotoEntity;
import com.dasiolmapserver.dasiolmap.storePhoto.repository.StorePhotoRepository;
import com.dasiolmapserver.dasiolmap.storeTag.domain.dto.StoreTagRequestDTO;
import com.dasiolmapserver.dasiolmap.storeTag.domain.dto.StoreTagResponseDTO;
import com.dasiolmapserver.dasiolmap.storeTag.domain.entity.StoreTagEntity;
import com.dasiolmapserver.dasiolmap.storeTag.repository.StoreTagRepository;

@Service
public class StoreTagService {
    @Autowired
    private StoreTagRepository storeTagRepository;

    @Autowired
    private DasiolStoreRepository dasiolStoreRepository;

    public List<StoreTagResponseDTO> insert(StoreTagRequestDTO request) {
        System.out.println("[debug] >>> storeTag service insert ");

        // 스토어 엔티티 조회
        DasiolStoreEntity store = dasiolStoreRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("스토어 없음"));

        StoreTagEntity storetag = StoreTagEntity.builder()
                .store(store)
                .storeTagName(request.getStoreTagName())
                .build();

        store.getStoreTags().add(storetag);
        storeTagRepository.save(storetag);

        Optional<StoreTagEntity> allStoretags = storeTagRepository.findByStore_StoreId(request.getStoreId());
        return allStoretags.stream()
                .map(e -> StoreTagResponseDTO.fromEntity(e))
                .toList();
    }

    public void delete(Integer storeId, Integer storeTagId) {

        System.out.println("[debug] >>> storeTag service delete storeTag ");
        StoreTagEntity storeTag = storeTagRepository.findById(storeTagId)
                .orElseThrow(() -> new RuntimeException("가게 태그가 존재하지 않습니다. ID=" + storeTagId));

        if (!storeTag.getStore().getStoreId().equals(storeId)) {
            throw new RuntimeException("잘못된 요청입니다.");
        }

        storeTagRepository.delete(storeTag);

    }
}
