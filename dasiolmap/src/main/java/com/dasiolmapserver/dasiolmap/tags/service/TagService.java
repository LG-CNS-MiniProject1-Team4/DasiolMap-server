package com.dasiolmapserver.dasiolmap.tags.service;

import com.dasiolmapserver.dasiolmap.tags.domain.entity.Tag;
import com.dasiolmapserver.dasiolmap.tags.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}
