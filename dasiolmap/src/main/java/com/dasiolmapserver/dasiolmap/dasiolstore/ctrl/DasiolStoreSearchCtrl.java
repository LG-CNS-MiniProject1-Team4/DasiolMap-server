package com.dasiolmapserver.dasiolmap.dasiolstore.ctrl;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.service.DasiolStoreSearchService;
import com.dasiolmapserver.dasiolmap.dasiolstore.service.DasiolStoreService; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.elastic.clients.elasticsearch._types.SortOrder;

@RestController
@RequestMapping("/api/v2/dasiolmap/search")
public class DasiolStoreSearchCtrl {
    
    @Autowired
    private DasiolStoreSearchService searchService;

    @Autowired // DasiolStoreService를 주입합니다.
    private DasiolStoreService dasiolStoreService;

    @GetMapping("/stores")
    public ResponseEntity<List<DasiolStoreResponseDTO>> searchStores(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "desc") String sort) { // sort 파라미터 추가

        // "asc"이면 오름차순(오래된순), 그 외에는 모두 내림차순(최신순)
        SortOrder sortOrder = "asc".equalsIgnoreCase(sort) ? SortOrder.Asc : SortOrder.Desc;

        return ResponseEntity.ok(searchService.searchStores(keyword, sortOrder));
    }

    @GetMapping("/stores/by-rating")
    public ResponseEntity<List<DasiolStoreResponseDTO>> getStoresOrderByAvgRating() {
        return ResponseEntity.ok(searchService.findStoresOrderByAvgRatingDesc());
    }

    @GetMapping("/stores/by-date")
    public ResponseEntity<List<DasiolStoreResponseDTO>> getStoresOrderByDate(
            @RequestParam(defaultValue = "desc") String sort) {
        SortOrder sortOrder = "asc".equalsIgnoreCase(sort) ? SortOrder.Asc : SortOrder.Desc;
        return ResponseEntity.ok(searchService.findStoresOrderByCreatedAt(sortOrder));
    }

    // 추가된 API
    @GetMapping("/stores/by-tag/{tagId}")
    public ResponseEntity<List<DasiolStoreResponseDTO>> searchStoresByTag(@PathVariable("tagId") Integer tagId) {
        return ResponseEntity.ok(dasiolStoreService.findStoresByTagId(tagId));
    }

    // 추가된 API
    @GetMapping("/stores/by-store-tag/{storeTagId}")
    public ResponseEntity<List<DasiolStoreResponseDTO>> searchStoresByStoreTag(@PathVariable("storeTagId") Integer storeTagId) {
        return ResponseEntity.ok(dasiolStoreService.findStoresByStoreTagId(storeTagId));
    }
}
