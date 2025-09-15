package com.dasiolmapserver.dasiolmap.dasiolstore.repository;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.document.DasiolStoreDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DasiolStoreSearchRepository extends ElasticsearchRepository<DasiolStoreDocument, Integer>{
    
}
