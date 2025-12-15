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
                .type(entity.getType() != null ? entity.getType().name() : null)
                .capacityTons(entity.getCapacityTons())
                .companyId(entity.getCompany() != null ? entity.getCompany().getId() : null)
                .build();
    }

    public static Vehicle toEntity(VehicleDTO dto) {
        if (dto == null) return null;

        Vehicle vehicle = new Vehicle();

        if (dto.getId() != null) {
            vehicle.setId(dto.getId());
        }

        vehicle.setRegistrationNumber(dto.getRegistrationNumber());
        vehicle.setCapacityTons(dto.getCapacityTons());


        if (dto.getType() != null) {
            vehicle.setType(VehicleType.valueOf(dto.getType()));
        }

        // company се set-ва в service слоя
        return vehicle;
    }
}