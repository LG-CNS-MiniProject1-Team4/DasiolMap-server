package com.dasiolmapserver.dasiolmap.dasiolstore.service;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.document.DasiolStoreDocument;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository; 

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

    @Autowired
    private DasiolStoreRepository dasiolStoreRepository;

    public List<DasiolStoreResponseDTO> searchStores(String keyword, SortOrder sortOrder) {
        Query query = Query.of(q -> q
            .multiMatch(mm -> mm
                .query(keyword)
                .fields("storeName", "address", "location")
            )
        );

        // 정렬 옵션 추가
        SortOptions sortOptions = new SortOptions.Builder()
                .field(f -> f
                        .field("createdAt") // 정렬 기준 필드
                        .order(sortOrder)   // 파라미터로 받은 정렬 순서
                        .missing("_last"))
                .build();

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .withSort(sortOptions) // 쿼리에 정렬 옵션 적용
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
                            .createdAt(doc.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    /**
     * 평균 별점이 높은 순으로 가게를 정렬하여 반환합니다.
     * @return 평균 별점 순으로 정렬된 가게 목록
     */
    public List<DasiolStoreResponseDTO> findStoresOrderByAvgRatingDesc() {
        Query query = Query.of(q -> q.matchAll(m -> m)); // 모든 문서를 대상으로 합니다.
        
        
        SortOptions sortOptions = new SortOptions.Builder()
                .field(f -> f
                        .field("avgRating")
                        .order(SortOrder.Desc)
                        .missing("_last")) // avgRating이 없는 데이터는 맨 뒤로 보냅니다.
                .build();

        NativeQuery nativeQuery = NativeQuery.builder()
            .withQuery(query)
            .withSort(sortOptions) // 수정된 정렬 옵션을 적용합니다.
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

     /**
     * 생성 시간 순으로 가게를 정렬하여 반환합니다.
     * @param sortOrder 정렬 순서 (ASC: 오래된순, DESC: 최신순)
     * @return 생성 시간 순으로 정렬된 가게 목록
     */
    public List<DasiolStoreResponseDTO> findStoresOrderByCreatedAt(SortOrder sortOrder) {
        Query query = Query.of(q -> q.matchAll(m -> m)); // 모든 문서를 대상으로 합니다.

        SortOptions sortOptions = new SortOptions.Builder()
                .field(f -> f
                        .field("createdAt")
                        .order(sortOrder)
                        .missing("_last")) // createdAt이 없는 데이터는 맨 뒤로 보냅니다.
                .build();

        NativeQuery nativeQuery = NativeQuery.builder()
            .withQuery(query)
            .withSort(sortOptions)
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
                    .createdAt(doc.getCreatedAt())
                    .build();
            })
            .collect(Collectors.toList());
    }

    // 주소 키워드를 파라미터로 받아 주소 키워드에 포함된 가게 목록 검색 구현 로직
    public List<DasiolStoreResponseDTO> searchStoresByAddress(String addressKeyword) {
    return dasiolStoreRepository.findByAddressContaining(addressKeyword).stream()
            .map(DasiolStoreResponseDTO::fromEntity)
            .collect(Collectors.toList());
    }
}