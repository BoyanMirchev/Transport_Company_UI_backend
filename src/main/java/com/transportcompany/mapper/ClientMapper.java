package com.transportcompany.mapper;

import com.transportcompany.dto.ClientDTO;
import com.transportcompany.entity.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client entity) {
        if (entity == null) return null;

        return ClientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .companyId(
                        entity.getCompany() != null ? entity.getCompany().getId() : null
                )
                .build();
    }

    public static Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        if (dto.getId() != null) {
            client.setId(dto.getId());
        }
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        return client;
    }
}
