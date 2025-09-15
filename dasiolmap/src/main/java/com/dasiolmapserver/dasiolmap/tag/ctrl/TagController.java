package com.dasiolmapserver.dasiolmap.tag.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagResponseDto;
import com.dasiolmapserver.dasiolmap.tag.domain.entity.Tag;
import com.dasiolmapserver.dasiolmap.tag.service.TagService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAllTags() { // 반환 타입을 DTO로 변경
        List<TagResponseDto> tags = tagService.findAll();
        return ResponseEntity.ok(tags);
    }
}
