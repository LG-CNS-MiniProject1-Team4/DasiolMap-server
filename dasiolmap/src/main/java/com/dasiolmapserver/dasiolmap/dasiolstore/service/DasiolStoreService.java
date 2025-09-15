package com.dasiolmapserver.dasiolmap.dasiolstore.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolreview.repository.DasiolReviewRepository;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;
import com.dasiolmapserver.dasiolmap.storeTag.domain.dto.StoreTagRequestDTO;
import com.dasiolmapserver.dasiolmap.storeTag.domain.entity.StoreTagEntity;
import com.dasiolmapserver.dasiolmap.storeTag.repository.StoreTagRepository;
import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagRequestDTO;
import com.dasiolmapserver.dasiolmap.tag.repository.TagRepository;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;
import com.dasiolmapserver.dasiolmap.user.repository.UserRepository;
import com.dasiolmapserver.dasiolmap.util.JwtProvider;

import jakarta.transaction.Transactional;


import com.dasiolmapserver.dasiolmap.dasiolstore.domain.document.DasiolStoreDocument;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreSearchRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DasiolStoreService {
    @Autowired
    private DasiolStoreRepository dasiolStoreRepository;

    @Autowired
    private DasiolStoreSearchRepository dasiolStoreSearchRepository;

    @Autowired
    private StoreTagRepository storeTagRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider provider;

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

    public DasiolStoreResponseDTO insert(DasiolStoreRequsetDTO request) {
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
    public DasiolStoreEntity update(Integer storeId, DasiolStoreRequsetDTO request) {
        System.out.println("[debug] >>> store service update ");
        DasiolStoreEntity entity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        entity.setStoreName(request.getStoreName());
        entity.setAddress(request.getAddress());
        entity.setLocation(request.getLocation());

        return entity;
    }


    @Transactional
    public DasiolStoreEntity addReview(Integer storeId, DasiolReviewRequsetDTO request) {
        System.out.println("[debug] >>> store service update ");
        DasiolStoreEntity entity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        // userId를 사용해 UserEntity를 찾습니다.
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        // toEntity 메소드에 store와 user를 모두 전달합니다.
        entity.getReviews().add(request.toEntity(entity, user));
        return entity;
    }
    @Transactional
    public DasiolStoreEntity addStoreTag(Integer storeId, StoreTagRequestDTO request) {
        System.out.println("[debug] >>> store service update : StoreTag ");
        DasiolStoreEntity entity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        entity.getStoreTags().add(storeTagRepository.findByStoreTagName(request.getStoreTagName()));

        return entity;
    }

    @Transactional
    public DasiolStoreEntity addTag(Integer storeId, TagRequestDTO request) {
        System.out.println("[debug] >>> store service update : Tag");
        DasiolStoreEntity entity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        entity.getTags().add(tagRepository.findByTagName(request.getTagName()));

        return entity;
    }

    @Transactional
    public void indexDasiolStores() {
        List<DasiolStoreEntity> stores = dasiolStoreRepository.findAll();
        for (DasiolStoreEntity store : stores) {
            DasiolStoreDocument document = DasiolStoreDocument.builder()
                    .storeId(store.getStoreId())
                    .storeName(store.getStoreName())
                    .address(store.getAddress())
                    .location(store.getLocation())
                    .build();
            dasiolStoreSearchRepository.save(document);
        }
    }
}