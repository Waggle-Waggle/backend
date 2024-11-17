package kr.co.onedayclass.repository;

import kr.co.onedayclass.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 사용자 이름으로 검색
    Optional<UserEntity> findByUsername(String username);

    // 사용자 이름의 존재 여부 확인
    boolean existsByUsername(String username);
}
