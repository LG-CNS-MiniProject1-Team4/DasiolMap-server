package com.dasiolmapserver.dasiolmap.tags.service;

import com.dasiolmapserver.dasiolmap.tags.domain.entity.Tag;
import com.dasiolmapserver.dasiolmap.tags.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.dasiolmapserver.dasiolmap.tags.domain.dto.TagResponseDto;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {
    private final TagRepository tagRepository;

    public List<TagResponseDto> findAll() {
        return tagRepository.findAll().stream()
                .map(TagResponseDto::new) // Tag를 TagResponseDto로 변환
                .collect(Collectors.toList());
    }
}
