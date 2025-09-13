package com.dasiolmapserver.dasiolmap.user.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity     // 이 클래스가 데이터베이스 테이블과 연결된 것임을 알려줍니다.
@Getter
@NoArgsConstructor
@Table(name = "users")  // 실제 DB 테이블 이름 지정
public class User {
    @Id     // id 필드가 이 테이블의 고유한 기본 키(primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값이 자동으로 생성됨
    private Long id;

    @Column(nullable = false, unique = true)    //@Column: 각 변수가 테이블의 어떤 컬럼에 해당하는지 설정
    private String email;

    @Column(name = "passwd", nullable = false) // ERD의 passwd 컬럼과 매핑
    private String password;

    @Column(nullable = false)
    private String nickname;
}
