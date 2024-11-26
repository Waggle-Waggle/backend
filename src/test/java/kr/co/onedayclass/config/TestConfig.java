package kr.co.onedayclass.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import kr.co.onedayclass.helper.UserHelper;

@TestConfiguration
public class TestConfig {

	@Bean
	public UserHelper userHelper() {
		return new UserHelper();
	}
}
