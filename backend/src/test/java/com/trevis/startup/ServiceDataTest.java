package com.trevis.startup;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.dto.service.ServiceDataCreationPayload;
import com.trevis.startup.interfaces.ServiceDataService;

@SpringBootTest
public class ServiceDataTest {
    
    @Autowired
    ServiceDataService serviceDataService;


    @Test
    protected void testServiceCreation() {
        var payload1 = new ServiceDataCreationPayload("Serviço 1", "Descrição", true);
        var payload2 = new ServiceDataCreationPayload("Serviço 2", "Descrição", true);
        assertDoesNotThrow(() -> serviceDataService.create(payload1));
        assertDoesNotThrow(() -> serviceDataService.create(payload2));
    }

    @Test
    protected void testServiceGet() {
        assertTrue(serviceDataService.get("", 1, 10).size() > 0);
        assertTrue(serviceDataService.get("Não existe", 1, 10).size() == 0);
        assertTrue(serviceDataService.get("1", 1, 10).size() == 1);
    }
}
