package com.dasiolmapserver.dasiolmap.dasiolstore.domain.document;
//  Elasticsearch에 저장될 데이터의 구조를 정의합니다.

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "dasiolstore")
public class DasiolStoreDocument {
    @Id
    private Integer storeId;

    @Field(type = FieldType.Text, name = "storeName")
    private String storeName;

    @Field(type = FieldType.Text, name = "address")
    private String address;

    @Field(type = FieldType.Text, name = "location")
    private String location;

    @Field(type = FieldType.Float, name = "avgRating")
    private Float avgRating;
}
