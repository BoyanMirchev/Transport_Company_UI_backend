package com.transportcompany.mapper;

import com.transportcompany.dto.ClientDTO;
import com.transportcompany.entity.Client;
import com.transportcompany.entity.TransportCompany;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    @Test
    void toDTO_shouldMapAllFields_whenCompanyExists() {
        TransportCompany company = new TransportCompany();
        company.setId(10L);

        Client client = new Client();
        client.setId(1L);
        client.setName("Ivan");
        client.setEmail("ivan@test.com");
        client.setCompany(company);

        ClientDTO dto = ClientMapper.toDTO(client);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Ivan", dto.getName());
        assertEquals("ivan@test.com", dto.getEmail());
        assertEquals(10L, dto.getCompanyId());
    }

    @Test
    void toDTO_shouldSetCompanyIdNull_whenCompanyMissing() {
        Client client = new Client();
        client.setId(2L);
        client.setName("Maria");
        client.setEmail("maria@test.com");
        client.setCompany(null);

        ClientDTO dto = ClientMapper.toDTO(client);

        assertNotNull(dto);
        assertEquals(2L, dto.getId());
        assertNull(dto.getCompanyId());
    }

    @Test
    void toDTO_shouldReturnNull_whenEntityIsNull() {
        assertNull(ClientMapper.toDTO(null));
    }

    @Test
    void toEntity_shouldMapFields_withoutId() {
        ClientDTO dto = ClientDTO.builder()
                .name("Petar")
                .email("petar@test.com")
                .build();

        Client client = ClientMapper.toEntity(dto);

        assertNotNull(client);
        assertNull(client.getId());
        assertEquals("Petar", client.getName());
        assertEquals("petar@test.com", client.getEmail());
    }

    @Test
    void toEntity_shouldSetId_whenIdProvided() {
        ClientDTO dto = ClientDTO.builder()
                .id(5L)
                .name("Georgi")
                .email("georgi@test.com")
                .build();

        Client client = ClientMapper.toEntity(dto);

        assertNotNull(client);
        assertEquals(5L, client.getId());
        assertEquals("Georgi", client.getName());
        assertEquals("georgi@test.com", client.getEmail());
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertNull(ClientMapper.toEntity(null));
    }
}
