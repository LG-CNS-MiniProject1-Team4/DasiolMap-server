package com.dasiolmapserver.dasiolmap.dasiolstore.service;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.document.DasiolStoreDocument;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreResponseDTO;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
@Service
public class DasiolStoreSearchService {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public List<DasiolStoreResponseDTO> searchStores(String keyword) {
        Query query = Query.of(q -> q
            .multiMatch(mm -> mm
                .query(keyword)
                .fields("storeName", "address", "location")
            )
        );

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .build();

        SearchHits<DasiolStoreDocument> searchHits = elasticsearchOperations.search(nativeQuery, DasiolStoreDocument.class);

        return searchHits.getSearchHits().stream()
                .map(hit -> {
                    DasiolStoreDocument doc = hit.getContent();
                    return DasiolStoreResponseDTO.builder()
                            .storeId(doc.getStoreId())
                            .storeName(doc.getStoreName())
                            .address(doc.getAddress())
                            .location(doc.getLocation())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
