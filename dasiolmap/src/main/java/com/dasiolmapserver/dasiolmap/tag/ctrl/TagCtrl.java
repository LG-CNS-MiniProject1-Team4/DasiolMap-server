package com.dasiolmapserver.dasiolmap.tag.ctrl;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagRequestDTO;
import com.dasiolmapserver.dasiolmap.tag.domain.dto.TagResponseDTO;
import com.dasiolmapserver.dasiolmap.tag.service.TagService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/dasiolmap/store/tag")
public class TagCtrl {
    @Autowired
    private TagService tagService;

    @PostMapping("/register")
    public ResponseEntity<List<TagResponseDTO>> register(
            @RequestBody TagRequestDTO request) {

        System.out.println("[debug] >>> store review photo ctrl path POST : /register ");
        System.out.println("[debug] param dto = " + request);

        List<TagResponseDTO> tags = tagService.insert(request);
        if (tags.size() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tags);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{storeId}/{tagId}")
    public ResponseEntity<Void> delete(
            @PathVariable("storeId") Integer storeId,
            @PathVariable("tagId") Integer tagId) {
        System.out.println("[debug] >>> Store review ctrl path DELETE : /delete ");
        System.out.println("[debug] param storeId = " + storeId);
        System.out.println("[debug] param reviewId = " + tagId);
        tagService.delete(storeId, tagId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
