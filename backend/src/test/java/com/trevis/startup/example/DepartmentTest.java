package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.services.DepartmentService;

@SpringBootTest
public class DepartmentTest {
    
    @Autowired
    DepartmentService departmentService;

    @Test
	void departmentTest() {
		assertEquals(departmentService.getAll().size(), 3);// tem três setores pré-definido
	}
    
}