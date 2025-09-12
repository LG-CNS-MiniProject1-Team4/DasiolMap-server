package com.dasiolmapserver.dasiolmap.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(nullable = false, length = 50)
    private String tagName;

    @OneToMany(mappedBy = "tag")
    private List<StoreTag> storeTags = new ArrayList<>();

    @Builder
    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
