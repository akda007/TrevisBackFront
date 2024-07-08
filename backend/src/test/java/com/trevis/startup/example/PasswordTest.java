package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.services.PasswordService;

@SpringBootTest
public class PasswordTest {
    
    @Autowired
    PasswordService passwordService;

    @Test
	void cryptographyTest() {
		assertEquals(passwordService.verifyCryptography("senha", passwordService.applyCryptography("senha")), true);//verificar com a criptografia são compativeis
		assertEquals(passwordService.verifyCryptography("senha", "senha"), false);
	}
	
	@Test
	void passwordTest() {
		assertEquals(passwordService.verifyRules("##"), 1);// esta no criteiro 1 de senha 
		assertEquals(passwordService.verifyRules("Senha"), 3);//..
		assertEquals(passwordService.verifyRules("Senha123"), 5);//..
	}
}