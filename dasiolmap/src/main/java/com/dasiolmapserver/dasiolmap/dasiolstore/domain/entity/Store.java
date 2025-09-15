package com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import com.dasiolmapserver.dasiolmap.tag.domain.entity.Tag;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String storeName;

    private String address;
    private String location;
    private Float avgRating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "store_tags",
        joinColumns = @JoinColumn(name = "store_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
