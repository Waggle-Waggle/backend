package kr.co.onedayclass.auth.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.onedayclass.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;

@Profile("default")
@RequiredArgsConstructor
@RestController
public class GenTokenController {

	private final JWTUtil jwtUtil;

	@PostMapping("/auth")
	public void genToken(HttpServletResponse response) {

		String jwt = jwtUtil.createJwt("aaa", "ADMIN", 60 * 60 * 1000L);

		// 쿠키 생성
		Cookie jwtCookie = new Cookie("Authorization", jwt);
		jwtCookie.setHttpOnly(true); // XSS 공격 방지
		jwtCookie.setSecure(true);  // HTTPS에서만 전송
		jwtCookie.setPath("/");     // 쿠키 경로 설정
		jwtCookie.setMaxAge(60 * 60); // 쿠키 유효 기간 (초 단위)

		// 응답에 쿠키 추가
		response.addCookie(jwtCookie);
	}
}
