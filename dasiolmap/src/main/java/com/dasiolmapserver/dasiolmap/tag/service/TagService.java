package com.dasiolmapserver.dasiolmap.tag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;

import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagRequestDTO;
import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagResponseDTO;
import com.dasiolmapserver.dasiolmap.tag.domain.entity.TagEntity;
import com.dasiolmapserver.dasiolmap.tag.repository.TagRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private DasiolStoreRepository dasiolStoreRepository;

    public List<TagResponseDTO> insert(@RequestBody @Valid TagRequestDTO request) {
        System.out.println("[debug] >>> Tag service insert ");

        // 스토어 엔티티 조회
        DasiolStoreEntity store = dasiolStoreRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("스토어 없음"));

        TagEntity tag = tagRepository.save(TagEntity.builder()
                .store(store)
                .tagName(request.getTagName())
                .build());

        store.getTags().add(tag);
        tagRepository.save(tag);

        Optional<TagEntity> allTags = tagRepository.findByStore_StoreId(request.getStoreId());
        return allTags.stream()
                .map(e -> TagResponseDTO.fromEntity(e))
                .toList();
    }

    public void delete(Integer storeId, Integer tagId) {

        System.out.println("[debug] >>> tag service delete tag ");
        TagEntity tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("태그가 존재하지 않습니다. ID=" + tagId));

        if (!tag.getStore().getStoreId().equals(storeId)) {
            throw new RuntimeException("잘못된 요청입니다.");
        }

        tagRepository.delete(tag);

    }
}
