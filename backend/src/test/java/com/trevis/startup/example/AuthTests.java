package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.interfaces.AuthService;

@SpringBootTest
class AuthTests {

	@Autowired
	LoginService authService;

	@Test
	void authTest() {
		assertEquals(authService.login("pessoa","senha"), false);// é para dar falso pq essa senha não pode ser cadastrada
	}

}