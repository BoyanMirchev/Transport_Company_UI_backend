package com.transportcompany.repository;

import com.transportcompany.entity.Client;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepositoryTest {

    private ClientRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ClientRepository();
    }

    @Test
    void testSaveAndFindById() {
        Client c = Client.builder()
                .name("John")
                .email("john@mail.com")
                .phone("0888123456")
                .address("Sofia")
                .build();

        repository.save(c);

        Client found = repository.findById(c.getId());

        assertNotNull(found);
        assertEquals("John", found.getName());
    }

    @Test
    void testDelete() {
        Client c = Client.builder()
                .name("ToDelete")
                .email("delete@mail.com")
                .build();

        repository.save(c);

        assertNotNull(repository.findById(c.getId()));

        repository.delete(c);

        assertNull(repository.findById(c.getId()));
    }
}

