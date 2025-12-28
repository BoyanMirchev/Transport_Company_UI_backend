package com.transportcompany.mapper;

import com.transportcompany.dto.VehicleDTO;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.entity.Vehicle;
import com.transportcompany.entity.VehicleType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleMapperTest {

    @Test
    void toDTO_shouldMapAllFields_whenCompanyExists() {
        TransportCompany company = new TransportCompany();
        company.setId(4L);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setRegistrationNumber("CA1234AB");
        vehicle.setType(VehicleType.Truck);
        vehicle.setCapacityTons(12.5);
        vehicle.setCompany(company);

        VehicleDTO dto = VehicleMapper.toDTO(vehicle);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("CA1234AB", dto.getRegistrationNumber());
        assertEquals(VehicleType.Truck, dto.getType());
        assertEquals(12.5, dto.getCapacityTons());
        assertEquals(4L, dto.getCompanyId());
    }

    @Test
    void toDTO_shouldSetCompanyIdNull_whenCompanyMissing() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(2L);
        vehicle.setRegistrationNumber("CB9876CD");
        vehicle.setType(VehicleType.Bus);
        vehicle.setCapacityTons(3.0);
        vehicle.setCompany(null);

        VehicleDTO dto = VehicleMapper.toDTO(vehicle);

        assertNotNull(dto);
        assertNull(dto.getCompanyId());
    }

    @Test
    void toDTO_shouldReturnNull_whenEntityIsNull() {
        assertNull(VehicleMapper.toDTO(null));
    }

    @Test
    void toEntity_shouldMapAllFields_whenTypeIsValid() {
        VehicleDTO dto = VehicleDTO.builder()
                .id(5L)
                .registrationNumber("PB1111AA")
                .type(VehicleType.Tanker)
                .capacityTons(8.0)
                .build();

        Vehicle vehicle = VehicleMapper.toEntity(dto);

        assertNotNull(vehicle);
        assertEquals(5L, vehicle.getId());
        assertEquals("PB1111AA", vehicle.getRegistrationNumber());
        assertEquals(VehicleType.Tanker, vehicle.getType());
        assertEquals(8.0, vehicle.getCapacityTons());
    }

    @Test
    void toEntity_shouldHandleNullType() {
        VehicleDTO dto = VehicleDTO.builder()
                .registrationNumber("BT2222BB")
                .capacityTons(5.0)
                .type(null)
                .build();

        Vehicle vehicle = VehicleMapper.toEntity(dto);

        assertNotNull(vehicle);
        assertNull(vehicle.getType());
        assertEquals("BT2222BB", vehicle.getRegistrationNumber());
        assertEquals(5.0, vehicle.getCapacityTons());
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertNull(VehicleMapper.toEntity(null));
    }
}
