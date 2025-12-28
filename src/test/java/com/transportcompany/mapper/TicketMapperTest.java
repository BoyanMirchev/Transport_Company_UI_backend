package com.transportcompany.mapper;

import com.transportcompany.dto.TicketDTO;
import com.transportcompany.entity.Price;
import com.transportcompany.entity.Ticket;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TicketMapperTest {

    @Test
    void toDTO_shouldMapAllFields_whenPriceExists() {
        Price price = new Price();
        price.setAmount(new BigDecimal("12.50"));

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setName("Standard Ticket");
        ticket.setPrice(price);

        TicketDTO dto = TicketMapper.toDTO(ticket);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Standard Ticket", dto.getName());
        assertEquals(new BigDecimal("12.50"), dto.getPrice());
    }

    @Test
    void toDTO_shouldSetPriceNull_whenPriceMissing() {
        Ticket ticket = new Ticket();
        ticket.setId(2L);
        ticket.setName("Free Ticket");
        ticket.setPrice(null);

        TicketDTO dto = TicketMapper.toDTO(ticket);

        assertNotNull(dto);
        assertEquals(2L, dto.getId());
        assertEquals("Free Ticket", dto.getName());
        assertNull(dto.getPrice());
    }

    @Test
    void toDTO_shouldReturnNull_whenEntityIsNull() {
        assertNull(TicketMapper.toDTO(null));
    }

    @Test
    void toEntity_shouldMapIdAndName_only() {
        TicketDTO dto = TicketDTO.builder()
                .id(5L)
                .name("VIP Ticket")
                .price(new BigDecimal("99.99")) // ТРЯБВА да се игнорира
                .build();

        Ticket ticket = TicketMapper.toEntity(dto);

        assertNotNull(ticket);
        assertEquals(5L, ticket.getId());
        assertEquals("VIP Ticket", ticket.getName());
        assertNull(ticket.getPrice()); // важно: price НЕ се сетва тук
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertNull(TicketMapper.toEntity(null));
    }
}
