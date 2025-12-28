package com.transportcompany.serviceImplTest;

import com.transportcompany.config.HibernateTestUtil;
import com.transportcompany.dto.TicketDTO;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.serviceImplTest.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceImplTest {

    private TicketServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new TicketServiceImpl();
    }

    @Test
    void getById_shouldReturnTicket_whenExists() {
        TicketDTO created = service.create(validTicketDTO());

        TicketDTO found = service.getById(created.getId());

        assertNotNull(found);
        assertEquals("Standard", found.getName());
        assertEquals(new BigDecimal("10.00"), found.getPrice());
    }

    @Test
    void getById_shouldThrow_whenTicketMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getById(999L));
    }

    @Test
    void create_shouldPersistTicket_andReuseExistingPrice() {
        TicketDTO dto = validTicketDTO();

        TicketDTO first = service.create(dto);
        TicketDTO second = service.create(dto);

        assertNotNull(first.getId());
        assertNotNull(second.getId());
        assertEquals(first.getPrice(), second.getPrice());
    }

    @Test
    void create_shouldThrow_whenDtoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.create(null));
    }

    @Test
    void create_shouldThrow_whenNameInvalid() {
        TicketDTO dto = validTicketDTO();
        dto.setName(" ");

        assertThrows(IllegalArgumentException.class,
                () -> service.create(dto));
    }

    @Test
    void create_shouldThrow_whenPriceInvalid() {
        TicketDTO dto = validTicketDTO();
        dto.setPrice(new BigDecimal("-1"));

        assertThrows(IllegalArgumentException.class,
                () -> service.create(dto));
    }

    @Test
    void update_shouldModifyTicket_whenValid() {
        TicketDTO created = service.create(validTicketDTO());

        TicketDTO update = validTicketDTO("Updated", new BigDecimal("25.00"));

        TicketDTO updated = service.update(created.getId(), update);

        assertEquals("Updated", updated.getName());
        assertEquals(new BigDecimal("25.00"), updated.getPrice());
    }

    @Test
    void update_shouldThrow_whenTicketMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, validTicketDTO()));
    }

    @Test
    void update_shouldThrow_whenDtoInvalid() {
        TicketDTO created = service.create(validTicketDTO());

        TicketDTO invalid = new TicketDTO();
        invalid.setName("");
        invalid.setPrice(new BigDecimal("10"));

        assertThrows(IllegalArgumentException.class,
                () -> service.update(created.getId(), invalid));
    }

    @Test
    void delete_shouldRemoveTicket() {
        TicketDTO created = service.create(validTicketDTO());

        service.delete(created.getId());

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(created.getId()));
    }

    @Test
    void delete_shouldThrow_whenTicketMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(999L));
    }

    // ===== helper =====

    private TicketDTO validTicketDTO() {
        return validTicketDTO("Standard", new BigDecimal("10.00"));
    }

    private TicketDTO validTicketDTO(String name, BigDecimal price) {
        TicketDTO dto = new TicketDTO();
        dto.setName(name);
        dto.setPrice(price);
        return dto;
    }
}
