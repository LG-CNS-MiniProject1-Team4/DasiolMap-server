package com.dasiolmapserver.dasiolmap.tags.ctrl;

import com.dasiolmapserver.dasiolmap.tags.domain.entity.Tag;
import com.dasiolmapserver.dasiolmap.tags.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.findAll();
        return ResponseEntity.ok(tags);
    }
}
