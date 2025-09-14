package com.dasiolmapserver.dasiolmap.dasiolstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequsetDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;
import com.dasiolmapserver.dasiolmap.util.JwtProvider;

import jakarta.transaction.Transactional;

@Service
public class DasiolStoreService {
    @Autowired
    private DasiolStoreRepository dasiolStoreRepository;

    @Autowired
    private JwtProvider provider;

    public List<DasiolStoreResponseDTO> select() {
        System.out.println("[debug] >>> blog service select ");
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
        System.out.println("[debug] >>> blog service insert ");
        DasiolStoreEntity store = dasiolStoreRepository.save(DasiolStoreEntity.builder()
                .storeId(request.getStoreId())
                .storeName(request.getStoreName())
                .address(request.getAddress())
                .location(request.getLocation())
                .build());

        return DasiolStoreResponseDTO.fromEntity(store);
    }

    public DasiolStoreResponseDTO findStore(String storeId) {
        System.out.println("[debug] >>> Store service findStore ");
        DasiolStoreEntity storeEntity = dasiolStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당 가게가 존재하지 않습니다."));
        System.out.println(">>>> service StoreEntity");
        System.out.println(storeEntity);

        DasiolStoreResponseDTO response = DasiolStoreResponseDTO.fromEntity(storeEntity);

        return response;
    }

    @Transactional
    public DasiolStoreEntity update(String storeid, DasiolStoreRequsetDTO request) {
        System.out.println("[debug] >>> blog service update ");
        request.setStoreId(storeid);
        DasiolStoreEntity entity = dasiolStoreRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        entity.setStoreName(request.getStoreName());
        entity.setAddress(request.getAddress());
        entity.setLocation(request.getLocation());

        return entity; // save() 호출 안 해도 @Transactional 이면 dirty checking으로 update 실행됨
    }
}
