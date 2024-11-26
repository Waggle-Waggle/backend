package kr.co.onedayclass.helper;

import static kr.co.onedayclass.user.domain.SocialType.*;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.onedayclass.user.domain.User;
import kr.co.onedayclass.user.repository.UserRepository;

public class UserHelper {

	@Autowired
	private UserRepository userRepository;

	public User create() {
		User user = User.builder()
			.username("waggle user")
			.email("user@waggle.com")
			.socialType(KAKAO)
			.build();

		return userRepository.save(user);
	}
}
