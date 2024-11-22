package kr.co.onedayclass.auth.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.onedayclass.auth.dto.CustomOAuth2User;
import kr.co.onedayclass.auth.dto.GoogleResponse;
import kr.co.onedayclass.auth.dto.KakaoResponse;
import kr.co.onedayclass.auth.dto.NaverResponse;
import kr.co.onedayclass.auth.dto.OAuth2Response;
import kr.co.onedayclass.auth.dto.UserDTO;
import kr.co.onedayclass.user.domain.User;
import kr.co.onedayclass.user.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	public CustomOAuth2UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println(oAuth2User.getAttributes());

		String registrationId = userRequest.getClientRegistration().getRegistrationId();

		OAuth2Response oAuth2Response = null;

		if (registrationId.equals("naver")) {
			oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
		} else if (registrationId.equals("google")) {
			oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
		} else if (registrationId.equals("kakao")) {
			oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
		} else {
			return null;
		}

		String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
		User existData = userRepository.findByUsername(username);

		if (existData == null) {

			// TODO: UserEntity
			// UserEntity userEntity = new UserEntity();
			// userEntity.setUsername(username);
			// userEntity.setEmail(oAuth2Response.getEmail());
			// userEntity.setName(oAuth2Response.getName());
			// userEntity.setRole("ROLE_USER");

			User user = User.builder()
				.username(username)
				.email(oAuth2Response.getEmail())
				.role("ROLE_USER")
				.build();

			userRepository.save(user);

			// TODO: userDTO
			// UserDTO userDTO = new UserDTO();
			// userDTO.setUsername(username);
			// userDTO.setName(oAuth2Response.getName());
			// userDTO.setRole("ROLE_USER");

			UserDTO userDTO = UserDTO.builder()
				.username(oAuth2Response.getName())
				.role("ROLE_USER")
				.build();

			return new CustomOAuth2User(userDTO);
		} else {

			existData.changeEmail(oAuth2Response.getEmail());
			// existData.setName(oAuth2Response.getName());

			userRepository.save(existData);

			// UserDTO userDTO = new UserDTO();
			// userDTO.setUsername(existData.getUsername());
			// userDTO.setName(oAuth2Response.getName());
			// userDTO.setRole(existData.getRole());

			UserDTO userDTO = UserDTO.builder()
				.username(oAuth2Response.getName())
				.role(existData.getRole())
				.build();

			return new CustomOAuth2User(userDTO);
		}
	}
}
