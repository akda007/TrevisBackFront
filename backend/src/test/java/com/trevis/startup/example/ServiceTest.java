package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.model.ServiceData;
import com.trevis.startup.example.services.ServiceService;

@SpringBootTest
public class ServiceTest {
    
    @Autowired
    ServiceService serviceService;

    @Test
	void serviceTest() {
		serviceService.add(new ServiceData("banana", "", false, null));
		serviceService.add(new ServiceData("banana", "", false, null));// add servico
		assertTrue(serviceService.get("banana",0,2).size() >= 2); // verifica se encontra servico add 
	}

}
