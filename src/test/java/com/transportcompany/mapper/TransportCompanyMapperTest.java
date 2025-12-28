package com.transportcompany.mapper;

import com.transportcompany.dto.TransportCompanyDTO;
import com.transportcompany.entity.TransportCompany;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportCompanyMapperTest {

    @Test
    void toEntity_shouldMapAllFields_withoutId() {
        TransportCompanyDTO dto = TransportCompanyDTO.builder()
                .name("Trans Co")
                .address("Sofia")
                .phone("0888123456")
                .build();

        TransportCompany entity = TransportCompanyMapper.toEntity(dto);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertEquals("Trans Co", entity.getName());
        assertEquals("Sofia", entity.getAddress());
        assertEquals("0888123456", entity.getPhone());
    }

    @Test
    void toEntity_shouldSetId_whenIdProvided() {
        TransportCompanyDTO dto = TransportCompanyDTO.builder()
                .id(5L)
                .name("Logistics Ltd")
                .address("Plovdiv")
                .phone("0899123456")
                .build();

        TransportCompany entity = TransportCompanyMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(5L, entity.getId());
        assertEquals("Logistics Ltd", entity.getName());
        assertEquals("Plovdiv", entity.getAddress());
        assertEquals("0899123456", entity.getPhone());
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertNull(TransportCompanyMapper.toEntity(null));
    }

    @Test
    void toDTO_shouldMapAllFields() {
        TransportCompany entity = new TransportCompany();
        entity.setId(3L);
        entity.setName("Fast Transport");
        entity.setAddress("Varna");
        entity.setPhone("0877123456");

        TransportCompanyDTO dto = TransportCompanyMapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(3L, dto.getId());
        assertEquals("Fast Transport", dto.getName());
        assertEquals("Varna", dto.getAddress());
        assertEquals("0877123456", dto.getPhone());
    }

    @Test
    void toDTO_shouldReturnNull_whenEntityIsNull() {
        assertNull(TransportCompanyMapper.toDTO(null));
    }
}
