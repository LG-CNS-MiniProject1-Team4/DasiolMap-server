package com.dasiolmapserver.dasiolmap.user.repository;

import com.dasiolmapserver.dasiolmap.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository는 클래스가 아닌 인터페이스로 만든다.
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository를 상속받으면 기본적인 데이터베이스 CRUD을 직접 만들지 않아도 자동으로 사용할수 있게 된다.


    // email을 통해 사용자를 찾는 메소드
    Optional<User> findByEmail(String email);
}
