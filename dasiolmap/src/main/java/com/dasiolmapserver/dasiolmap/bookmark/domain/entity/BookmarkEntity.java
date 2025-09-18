package com.dasiolmapserver.dasiolmap.bookmark.domain.entity;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference; 

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class BookmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookmarkId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userEmail", referencedColumnName = "email")
    @JsonBackReference 
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store")
    @JsonBackReference 
    private DasiolStoreEntity store;

}
