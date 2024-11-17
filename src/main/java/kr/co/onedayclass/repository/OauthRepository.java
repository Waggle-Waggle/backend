package kr.co.onedayclass.repository;

import kr.co.onedayclass.entity.OauthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRepository extends JpaRepository<OauthEntity, Long> {

    // 사용자 이름으로 검색
    OauthEntity findByUsername(String username);

}
