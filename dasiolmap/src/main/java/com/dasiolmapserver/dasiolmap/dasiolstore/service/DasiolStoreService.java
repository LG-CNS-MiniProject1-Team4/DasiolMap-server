package com.dasiolmapserver.dasiolmap.dasiolstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewRequestDTO;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequestDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;
import com.dasiolmapserver.dasiolmap.storeTag.domain.dto.StoreTagRequestDTO;

import com.dasiolmapserver.dasiolmap.storeTag.repository.StoreTagRepository;
import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagRequestDTO;
import com.dasiolmapserver.dasiolmap.tag.repository.TagRepository;

import jakarta.transaction.Transactional;

@Service
public class DasiolStoreService {
    @Autowired
    private DasiolStoreRepository dasiolStoreRepository;

    @Autowired
    private StoreTagRepository storeTagRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<DasiolStoreResponseDTO> select() {
        System.out.println("[debug] >>> store service select ");
        List<DasiolStoreEntity> list = dasiolStoreRepository.findAll();

        return list.stream()
                .map(entity -> DasiolStoreResponseDTO.builder()
                        .storeId(entity.getStoreId())
                        .storeName(entity.getStoreName())
                        .address(entity.getAddress())
                        .location(entity.getLocation())
                        .build())
                .toList();
    }

    public DasiolStoreResponseDTO insert(DasiolStoreRequestDTO request) {
        System.out.println("[debug] >>> store service insert ");

        DasiolStoreEntity store = dasiolStoreRepository.save(DasiolStoreEntity.builder()
                .storeName(request.getStoreName())
                .address(request.getAddress())
                .location(request.getLocation())
                .build());

        return DasiolStoreResponseDTO.fromEntity(store);
    }

    public DasiolStoreResponseDTO findStore(Integer storeId) {
        System.out.println("[debug] >>> Store service findStore ");
        DasiolStoreEntity storeEntity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당 가게가 존재하지 않습니다."));
        System.out.println(">>>> service StoreEntity");
        System.out.println(storeEntity);

        DasiolStoreResponseDTO response = DasiolStoreResponseDTO.fromEntity(storeEntity);

        return response;
    }

    @Transactional
    public DasiolStoreEntity update(Integer storeId, DasiolStoreRequestDTO request) {
        System.out.println("[debug] >>> store service update ");
        DasiolStoreEntity entity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        entity.setStoreName(request.getStoreName());
        entity.setAddress(request.getAddress());
        entity.setLocation(request.getLocation());
        return entity; // save() 호출 안 해도 @Transactional 이면 dirty checking으로 update 실행됨
    }

    @Transactional
    public DasiolStoreEntity update(Integer storeId, StoreTagRequestDTO request) {
        System.out.println("[debug] >>> store service update : StoreTag ");
        DasiolStoreEntity entity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        entity.getStoreTags().add(storeTagRepository.findByStoreTagName(request.getStoreTagName()));

        return entity;
    }

    @Transactional
    public DasiolStoreEntity update(Integer storeId, TagRequestDTO request) {
        System.out.println("[debug] >>> store service update : Tag");
        DasiolStoreEntity entity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        entity.getTags().add(tagRepository.findByTagName(request.getTagName()));

        return entity;
    }

    @Transactional
    public DasiolStoreEntity update(Integer storeId, DasiolReviewRequestDTO request) {
        System.out.println("[debug] >>> store service update ");
        DasiolStoreEntity entity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));
        entity.getReviews().add(request.toEntity(entity));
        return entity;
    }
}
