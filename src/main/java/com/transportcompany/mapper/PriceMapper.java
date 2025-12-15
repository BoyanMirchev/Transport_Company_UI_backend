package com.transportcompany.mapper;

import com.transportcompany.dto.PriceDTO;
import com.transportcompany.entity.Price;

public class PriceMapper {

    public static PriceDTO toDTO(Price p) {
        if (p == null) return null;

        return PriceDTO.builder()
                .id(p.getId())
                .amount(p.getAmount())
                .build();
    }

    public static Price toEntity(PriceDTO dto) {
        if (dto == null) return null;

        Price p = new Price();
        p.setId(dto.getId());
        p.setAmount(dto.getAmount());
        return p;
    }
}