package kr.co.onedayclass.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfig.class)
public abstract class WaggleTestBase {
}
