package kr.co.onedayclass.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.onedayclass.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
