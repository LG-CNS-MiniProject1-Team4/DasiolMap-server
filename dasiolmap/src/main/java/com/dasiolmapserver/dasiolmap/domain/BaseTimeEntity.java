package com.dasiolmapserver.dasiolmap.domain;
//[공통 속성 관리]
// 모든 엔티티가 공통으로 사용할 생성 및 수정 시간 필드를 관리하는 클래스

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // 이 클래스를 상속받는 엔티티들은 아래 필드들을 컬럼으로 인식하게 됩니다.
@EntityListeners(AuditingEntityListener.class) // 시간에 대한 Auditing 기능을 포함시킵니다.

public abstract class BaseTimeEntity {
    
    @CreatedDate // 엔티티가 생성되어 저장될 때 시간이 자동 저장됩니다.
    private LocalDateTime createdAt;

    @LastModifiedDate   // 조회한 엔티티의 값을 변경할 때 시간이 자동 저장됩니다.
    private LocalDateTime updatedAt;
}



// 추가해야 할부분
/*
 * DasiolmapApplication.java 파일에 @EnableJpaAuditing 어노테이션을 추가해야 BaseTimeEntity가 정상적으로 동작합니다.
 */