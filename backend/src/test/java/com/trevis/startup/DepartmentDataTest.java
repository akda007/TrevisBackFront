package com.trevis.startup;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.exceptions.NotFoundException;
import com.trevis.startup.interfaces.DepartmentDataService;

@SpringBootTest
public class DepartmentDataTest {
    
    @Autowired
    DepartmentDataService departmentDataService;


    @Test
    protected void testDepartmentGetAll() {
        assertTrue(departmentDataService.getAll().size() > 0);
    }

    @Test
    protected void testDepartmentGetById() {
        assertNotNull(departmentDataService.getById(1L));
        assertThrows(NotFoundException.class, () -> departmentDataService.getById(91299219L));
    }
}
