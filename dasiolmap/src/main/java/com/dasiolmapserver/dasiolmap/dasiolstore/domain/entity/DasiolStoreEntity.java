package com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DasiolStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String storeId;

    @Column(nullable = false, length = 10)
    private String storeName;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false, length = 150)
    private String location;

    // 외래키 설정
}
