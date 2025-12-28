package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.ClientDTO;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.serviceImplTest.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {

    private ClientServiceImpl service;
    private TransportCompanyRepository companyRepository;
    private TransportCompany company;

    @BeforeEach
    void setUp() {


        service = new ClientServiceImpl();
        companyRepository = new TransportCompanyRepository();

        company = new TransportCompany();
        company.setName("Test Company");
        companyRepository.save(company);
    }

    @Test
    void getById_shouldReturnClient_whenExists() {
        ClientDTO created = service.create(validClientDTO());

        ClientDTO found = service.getById(created.getId());

        assertNotNull(found);
        assertEquals("Ivan", found.getName());
        assertEquals("ivan@test.com", found.getEmail());
    }

    @Test
    void getById_shouldThrow_whenClientMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getById(999L));
    }

    @Test
    void create_shouldPersistClient_whenValid() {
        ClientDTO result = service.create(validClientDTO());

        assertNotNull(result.getId());
        assertEquals(company.getId(), result.getCompanyId());
    }

    @Test
    void create_shouldThrow_whenCompanyMissing() {
        ClientDTO dto = validClientDTO();
        dto.setCompanyId(999L);

        assertThrows(EntityNotFoundException.class,
                () -> service.create(dto));
    }

    @Test
    void update_shouldModifyClient_whenValid() {
        ClientDTO created = service.create(validClientDTO());

        ClientDTO update = new ClientDTO();
        update.setName("Updated");
        update.setEmail("updated@test.com");

        ClientDTO updated = service.update(created.getId(), update);

        assertEquals("Updated", updated.getName());
        assertEquals("updated@test.com", updated.getEmail());
    }

    @Test
    void update_shouldThrow_whenClientMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, validClientDTO()));
    }

    @Test
    void update_shouldThrow_whenNewCompanyMissing() {
        ClientDTO created = service.create(validClientDTO());

        ClientDTO update = new ClientDTO();
        update.setName("Test");
        update.setEmail("test@test.com");
        update.setCompanyId(999L);

        assertThrows(EntityNotFoundException.class,
                () -> service.update(created.getId(), update));
    }

    @Test
    void delete_shouldRemoveClient() {
        ClientDTO created = service.create(validClientDTO());

        service.delete(created.getId());

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(created.getId()));
    }

    @Test
    void delete_shouldThrow_whenClientMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(999L));
    }

    @Test
    void findByEmail_shouldThrow_whenMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.findByEmail("missing@test.com"));
    }

    // ===== helper =====

    private ClientDTO validClientDTO() {
        ClientDTO dto = new ClientDTO();
        dto.setName("Ivan");
        dto.setEmail("ivan@test.com");
        dto.setCompanyId(company.getId());
        return dto;
    }
}
