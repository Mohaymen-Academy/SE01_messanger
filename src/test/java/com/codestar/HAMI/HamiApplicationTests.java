package com.codestar.HAMI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HamiApplicationTests {

	@Test
	void contextLoads() {
		int a = 10_000_000;
		System.out.println(a * 2);
	}
}
