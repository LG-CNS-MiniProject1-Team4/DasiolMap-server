package com.dasiolmapserver.dasiolmap.dasiolstore.ctrl;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.Store;
import com.dasiolmapserver.dasiolmap.dasiolstore.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<Store>> getStoresByTags(@RequestParam(required = false) List<String> tags) {
        List<Store> stores = storeService.findStoresByTags(tags);
        return ResponseEntity.ok(stores);
    }
}
