package com.transportcompany.serviceImplTest;

import com.transportcompany.config.HibernateTestUtil;
import com.transportcompany.dto.TransportCompanyDTO;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.serviceImplTest.impl.TransportCompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportCompanyServiceImplTest {

    private TransportCompanyServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new TransportCompanyServiceImpl();
    }

    @Test
    void getById_shouldReturnCompany_whenExists() {
        TransportCompanyDTO created = service.create(validCompanyDTO());

        TransportCompanyDTO found = service.getById(created.getId());

        assertNotNull(found);
        assertEquals("Test Company", found.getName());
    }

    @Test
    void getById_shouldThrow_whenCompanyMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getById(999L));
    }


    @Test
    void create_shouldPersistCompany_whenValid() {
        TransportCompanyDTO result = service.create(validCompanyDTO());

        assertNotNull(result.getId());
        assertEquals("Test Company", result.getName());
    }

    @Test
    void create_shouldThrow_whenDtoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.create(null));
    }

    @Test
    void create_shouldThrow_whenNameInvalid() {
        TransportCompanyDTO dto = validCompanyDTO();
        dto.setName(" ");

        assertThrows(IllegalArgumentException.class,
                () -> service.create(dto));
    }

    @Test
    void update_shouldModifyCompany_whenValid() {
        TransportCompanyDTO created = service.create(validCompanyDTO());

        TransportCompanyDTO update = new TransportCompanyDTO();
        update.setId(created.getId());
        update.setName("Updated Company");
        update.setAddress("Plovdiv");
        update.setPhone("0888999999");
        update.setRevenue(new BigDecimal("50000"));

        TransportCompanyDTO updated = service.update(update);

        assertEquals("Updated Company", updated.getName());
        assertEquals("Plovdiv", updated.getAddress());
        assertEquals("0888999999", updated.getPhone());

    }

    @Test
    void update_shouldThrow_whenDtoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.update(null));
    }

    @Test
    void update_shouldThrow_whenIdMissing() {
        TransportCompanyDTO dto = new TransportCompanyDTO();
        dto.setName("No ID");

        assertThrows(IllegalArgumentException.class,
                () -> service.update(dto));
    }

    @Test
    void update_shouldThrow_whenCompanyMissing() {
        TransportCompanyDTO dto = validCompanyDTO();
        dto.setId(999L);

        assertThrows(EntityNotFoundException.class,
                () -> service.update(dto));
    }

    @Test
    void delete_shouldRemoveCompany() {
        TransportCompanyDTO created = service.create(validCompanyDTO());

        service.delete(created.getId());

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(created.getId()));
    }

    @Test
    void delete_shouldThrow_whenCompanyMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(999L));
    }

    // ===== helper =====

    private TransportCompanyDTO validCompanyDTO() {
        TransportCompanyDTO dto = new TransportCompanyDTO();
        dto.setName("Test Company");
        dto.setAddress("Sofia");
        dto.setPhone("0888123456");
        dto.setRevenue(new BigDecimal("100000"));
        return dto;
    }
}
