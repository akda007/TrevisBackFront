package com.trevis.startup;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.dto.user.UserDataCreationPayload;
import com.trevis.startup.dto.user.UserDataUpdatePayload;
import com.trevis.startup.exceptions.NotFoundException;
import com.trevis.startup.interfaces.UserDataService;

@SpringBootTest
public class UserDataTest {

    @Autowired
    UserDataService userDataService;


    @Test
    protected void testUserCreation() {
        var payload = new UserDataCreationPayload("murylo", 1L, 2);
        assertDoesNotThrow(() -> userDataService.create(payload));
    }

    @Test
    protected void testUserGetById() {
        assertNotNull(userDataService.getById(1L));
        assertThrows(NotFoundException.class, () -> userDataService.getById(99999L));
    }

    @Test
    protected void testUserGetByUsername() {
        assertNotNull(userDataService.getByUsername("murylo"));
        assertThrows(NotFoundException.class, () -> userDataService.getByUsername("paiDoNilton"));
    }

    @Test
    protected void testUserUpdate() {
        var payload = new UserDataUpdatePayload("muryloNew", null, null, "Password1!");

        assertDoesNotThrow(() -> userDataService.updateUser(1L, payload));

        var user = userDataService.getById(1L);
        assertEquals("muryloNew", user.getUsername());
        assertEquals("muryloNew", user.getUsername());
        assertEquals("muryloNew", user.getUsername());
        assertEquals("muryloNew", user.getUsername());
    }
}
