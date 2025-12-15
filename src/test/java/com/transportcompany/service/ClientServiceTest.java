package com.transportcompany.service;

import com.transportcompany.entity.Client;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {

    private final ClientService service = new ClientServiceImpl();

    @Test
    void testCreateValidClient() {
        Client c = Client.builder()
                .name("Mario")
                .email("mario@mail.com")
                .build();

        assertDoesNotThrow(() -> service.create(c));
        assertNotNull(c.getId());
    }

    @Test
    void testCreateInvalidClientName() {
        Client c = Client.builder()
                .name("")
                .email("x@mail.com")
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> service.create(c));
    }

    @Test
    void testFindByIdNotFound() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getById(9999));
    }
}
