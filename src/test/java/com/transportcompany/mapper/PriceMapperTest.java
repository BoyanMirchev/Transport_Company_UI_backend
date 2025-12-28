package com.transportcompany.mapper;

import com.transportcompany.dto.PriceDTO;
import com.transportcompany.entity.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceMapperTest {

    @Test
    void toDTO_shouldMapAllFields() {
        Price price = new Price();
        price.setId(1L);
        price.setAmount(new BigDecimal("15.50"));

        PriceDTO dto = PriceMapper.toDTO(price);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(new BigDecimal("15.50"), dto.getAmount());
    }

    @Test
    void toDTO_shouldReturnNull_whenEntityIsNull() {
        assertNull(PriceMapper.toDTO(null));
    }

    @Test
    void toEntity_shouldMapAllFields() {
        PriceDTO dto = PriceDTO.builder()
                .id(5L)
                .amount(new BigDecimal("99.99"))
                .build();

        Price price = PriceMapper.toEntity(dto);

        assertNotNull(price);
        assertEquals(5L, price.getId());
        assertEquals(new BigDecimal("99.99"), price.getAmount());
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertNull(PriceMapper.toEntity(null));
    }
}
