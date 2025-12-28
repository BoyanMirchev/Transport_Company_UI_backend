package com.transportcompany.mapper;

import com.transportcompany.dto.VehicleDTO;
import com.transportcompany.entity.Vehicle;
import com.transportcompany.entity.VehicleType;

public class VehicleMapper {

    public static VehicleDTO toDTO(Vehicle entity) {
        if (entity == null) return null;

        return VehicleDTO.builder()
                .id(entity.getId())
                .registrationNumber(entity.getRegistrationNumber())
                .type(entity.getType())
                .capacityTons(entity.getCapacityTons())
                .companyId(entity.getCompany() != null ? entity.getCompany().getId() : null)
                .build();
    }

    public static Vehicle toEntity(VehicleDTO dto) {
        if (dto == null) return null;

        Vehicle v = new Vehicle();
        v.setId(dto.getId());
        v.setRegistrationNumber(dto.getRegistrationNumber());
        v.setCapacityTons(dto.getCapacityTons());

        if (dto.getType() != null) {
            try {
                v.setType((dto.getType()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Invalid vehicle type: " + dto.getType()
                );
            }
        }

        return v;
    }
}

