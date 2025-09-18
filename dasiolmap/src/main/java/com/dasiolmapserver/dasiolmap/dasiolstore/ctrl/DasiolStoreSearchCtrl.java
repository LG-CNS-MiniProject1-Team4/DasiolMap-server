package com.dasiolmapserver.dasiolmap.dasiolstore.ctrl;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.service.DasiolStoreSearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.elastic.clients.elasticsearch._types.SortOrder;

@RestController
@RequestMapping("/api/v2/dasiolmap/search")
public class DasiolStoreSearchCtrl {
    
    @Autowired
    private DasiolStoreSearchService searchService;

    @GetMapping("/stores")
    public ResponseEntity<List<DasiolStoreResponseDTO>> searchStores(@RequestParam String keyword) {
        return ResponseEntity.ok(searchService.searchStores(keyword));
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
}
