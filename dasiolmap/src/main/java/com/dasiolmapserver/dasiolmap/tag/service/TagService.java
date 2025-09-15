package com.dasiolmapserver.dasiolmap.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagResponseDto;
import com.dasiolmapserver.dasiolmap.tag.domain.entity.Tag;
import com.dasiolmapserver.dasiolmap.tag.repository.TagRepository;

import java.util.List;
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
