package com.transportcompany.mapper;

import com.transportcompany.dto.TransportCompanyDTO;
import com.transportcompany.entity.TransportCompany;

public class TransportCompanyMapper {

    public static TransportCompany toEntity(TransportCompanyDTO dto) {
        if (dto == null) return null;

        TransportCompany entity = new TransportCompany();

        // id се сетва САМО ако не е null
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }

        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());

        return entity;
    }

    public static TransportCompanyDTO toDTO(TransportCompany entity) {
        if (entity == null) return null;

        return TransportCompanyDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .build();
    }
}
