package com.dasiolmapserver.dasiolmap.domain;
// [Users 테이블]

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")

public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String passwd;

    @Column(nullable = false, unique = true)
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder
    public User(String email, String passwd, String nickname) {
        this.email = email;
        this.passwd = passwd;
        this.nickname = nickname;
    }
    
}
