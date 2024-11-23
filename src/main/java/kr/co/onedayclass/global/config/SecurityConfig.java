package kr.co.onedayclass.global.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.onedayclass.auth.handler.CustomSuccessHandler;
import kr.co.onedayclass.auth.service.CustomOAuth2UserService;
import kr.co.onedayclass.jwt.JWTFilter;
import kr.co.onedayclass.jwt.JWTUtil;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

	//AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
	private final CustomOAuth2UserService customOAuth2UserService;
	private final CustomSuccessHandler customSuccessHandler;
	private final JWTUtil jwtUtil;

	public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler,
		JWTUtil jwtUtil) {
		this.customOAuth2UserService = customOAuth2UserService;
		this.customSuccessHandler = customSuccessHandler;
		this.jwtUtil = jwtUtil;
	}

	@Bean
	public JWTFilter jwtFilter() {
		return new JWTFilter(jwtUtil);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.cors((cors) -> cors
				.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration configuration = new CorsConfiguration();

						configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
						configuration.setAllowedMethods(Collections.singletonList("*"));
						configuration.setAllowCredentials(true);
						configuration.setAllowedHeaders(Collections.singletonList("*"));
						configuration.setMaxAge(3600L);

						configuration.setExposedHeaders(Collections.singletonList("Authorization"));

						return configuration;
					}
				}));

		//csrf disable
		http
			.csrf((auth) -> auth.disable());

		//From 로그인 방식 disable
		http
			.formLogin((auth) -> auth.disable());

		//http basic 인증 방식 disable
		http
			.httpBasic((auth) -> auth.disable());

		//경로별 인가 작업
		http
			.authorizeHttpRequests((auth) -> auth
				// TODO
				.requestMatchers("/", "/oauth2/**", "/login/**", "/error", "/auth/**", "/auth").permitAll()
				.requestMatchers("/meetings/**", "/meetings").authenticated()
				.requestMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated());

		//JWTFilter 추가
		http
			.addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);

		// http
		// 	.oauth2Login((oauth2) -> oauth2
		// 		.loginPage("/login")
		// 		.userInfoEndpoint(userInfoEndpointConfig ->
		// 			userInfoEndpointConfig.userService(customOAuth2UserService))
		// 		.successHandler(customSuccessHandler));
		// //세션 설정
		// http
		// 	.sessionManagement((session) -> session
		// 		.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

}