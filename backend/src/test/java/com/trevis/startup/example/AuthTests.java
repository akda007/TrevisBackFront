package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.services.AuthService;

@SpringBootTest
class AuthTests {

	@Autowired
	AuthService authService;

	@Test
	void authTest() {
		assertEquals(authService.login("pessoa","senha"), false);// é para dar falso pq essa senha não pode ser cadastrada
	}

}