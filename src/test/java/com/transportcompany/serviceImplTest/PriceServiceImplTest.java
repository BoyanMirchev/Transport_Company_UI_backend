package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.PriceDTO;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.serviceImplTest.impl.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceServiceImplTest {

    private PriceServiceImpl service;

    @BeforeEach
    void setUp() {

        service = new PriceServiceImpl();
    }

    @Test
    void getById_shouldReturnPrice_whenExists() {
        PriceDTO created = service.create(validPriceDTO());

        PriceDTO found = service.getById(created.getId());

        assertNotNull(found);
        assertEquals(new BigDecimal("15.50"), found.getAmount());
    }

    @Test
    void getById_shouldThrow_whenIdInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> service.getById(0L));

        assertThrows(IllegalArgumentException.class,
                () -> service.getById(null));
    }

    @Test
    void getById_shouldThrow_whenPriceMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getById(999L));
    }

    @Test
    void create_shouldPersistPrice_whenValid() {
        PriceDTO result = service.create(validPriceDTO());

        assertNotNull(result.getId());
        assertEquals(new BigDecimal("15.50"), result.getAmount());
    }

    @Test
    void create_shouldThrow_whenDtoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.create(null));
    }

    @Test
    void create_shouldThrow_whenAmountNull() {
        PriceDTO dto = new PriceDTO();
        dto.setAmount(null);

        assertThrows(IllegalArgumentException.class,
                () -> service.create(dto));
    }

    @Test
    void create_shouldThrow_whenAmountNegative() {
        PriceDTO dto = new PriceDTO();
        dto.setAmount(new BigDecimal("-5"));

        assertThrows(IllegalArgumentException.class,
                () -> service.create(dto));
    }

    @Test
    void update_shouldModifyPrice_whenValid() {
        PriceDTO created = service.create(validPriceDTO());

        PriceDTO update = new PriceDTO();
        update.setAmount(new BigDecimal("99.99"));

        PriceDTO updated = service.update(created.getId(), update);

        assertEquals(new BigDecimal("99.99"), updated.getAmount());
    }

    @Test
    void update_shouldThrow_whenIdInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> service.update(0L, validPriceDTO()));

        assertThrows(IllegalArgumentException.class,
                () -> service.update(null, validPriceDTO()));
    }

    @Test
    void update_shouldThrow_whenPriceMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, validPriceDTO()));
    }

    @Test
    void update_shouldThrow_whenDtoInvalid() {
        PriceDTO created = service.create(validPriceDTO());

        PriceDTO invalid = new PriceDTO();
        invalid.setAmount(new BigDecimal("-1"));

        assertThrows(IllegalArgumentException.class,
                () -> service.update(created.getId(), invalid));
    }

    @Test
    void delete_shouldRemovePrice() {
        PriceDTO created = service.create(validPriceDTO());

        service.delete(created.getId());

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(created.getId()));
    }

    @Test
    void delete_shouldThrow_whenPriceMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(999L));
    }

    // ===== helper =====

    private PriceDTO validPriceDTO() {
        PriceDTO dto = new PriceDTO();
        dto.setAmount(new BigDecimal("15.50"));
        return dto;
    }

    private PriceDTO validPriceDTO(BigDecimal amount) {
        PriceDTO dto = new PriceDTO();
        dto.setAmount(amount);
        return dto;
    }
}
