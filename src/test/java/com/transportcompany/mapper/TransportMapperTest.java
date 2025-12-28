package com.transportcompany.mapper;

import com.transportcompany.dto.TransportDTO;
import com.transportcompany.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransportMapperTest {

    @Test
    void toDTO_shouldMapAllFields_whenAllRelationsExist() {
        Client client = new Client();
        client.setId(1L);

        Employee employee = new Employee();
        employee.setId(2L);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(3L);

        TransportCompany company = new TransportCompany();
        company.setId(4L);

        Price price = new Price();
        price.setId(5L);

        Transport transport = new Transport();
        transport.setId(10L);
        transport.setStartPoint("Sofia");
        transport.setEndPoint("Plovdiv");
        transport.setDepartureDate(LocalDate.of(2025, 1, 10));
        transport.setArrivalDate(LocalDate.of(2025, 1, 10));
        transport.setCargoDescription("Electronics");
        transport.setCargoWeight(1200.5);
        transport.setPaid(true);
        transport.setClient(client);
        transport.setEmployee(employee);
        transport.setVehicle(vehicle);
        transport.setCompany(company);
        transport.setPrice(price);

        TransportDTO dto = TransportMapper.toDTO(transport);

        assertNotNull(dto);
        assertEquals(10L, dto.getId());
        assertEquals("Sofia", dto.getStartPoint());
        assertEquals("Plovdiv", dto.getEndPoint());
        assertEquals(LocalDate.of(2025, 1, 10), dto.getDepartureDate());
        assertEquals(LocalDate.of(2025, 1, 10), dto.getArrivalDate());
        assertEquals("Electronics", dto.getCargoDescription());
        assertEquals(1200.5, dto.getCargoWeight());
        assertTrue(dto.isPaid());
        assertEquals(1L, dto.getClientId());
        assertEquals(2L, dto.getEmployeeId());
        assertEquals(3L, dto.getVehicleId());
        assertEquals(4L, dto.getCompanyId());
        assertEquals(5L, dto.getPriceId());
    }

    @Test
    void toDTO_shouldSetAllRelationIdsNull_whenRelationsMissing() {
        Transport transport = new Transport();
        transport.setId(20L);
        transport.setStartPoint("Varna");
        transport.setEndPoint("Burgas");
        transport.setPaid(false);

        TransportDTO dto = TransportMapper.toDTO(transport);

        assertNotNull(dto);
        assertEquals(20L, dto.getId());
        assertNull(dto.getClientId());
        assertNull(dto.getEmployeeId());
        assertNull(dto.getVehicleId());
        assertNull(dto.getCompanyId());
        assertNull(dto.getPriceId());
    }

    @Test
    void toDTO_shouldReturnNull_whenEntityIsNull() {
        assertNull(TransportMapper.toDTO(null));
    }

    @Test
    void toEntity_shouldMapPrimitiveFields_only() {
        TransportDTO dto = TransportDTO.builder()
                .id(7L)
                .startPoint("Ruse")
                .endPoint("Sofia")
                .departureDate(LocalDate.of(2025, 2, 1))
                .arrivalDate(LocalDate.of(2025, 2, 1))
                .cargoDescription("Furniture")
                .cargoWeight(800.0)
                .paid(true)
                .clientId(1L)     // трябва да се игнорира
                .employeeId(2L)
                .vehicleId(3L)
                .companyId(4L)
                .priceId(5L)
                .build();

        Transport transport = TransportMapper.toEntity(dto);

        assertNotNull(transport);
        assertEquals(7L, transport.getId());
        assertEquals("Ruse", transport.getStartPoint());
        assertEquals("Sofia", transport.getEndPoint());
        assertEquals(LocalDate.of(2025, 2, 1), transport.getDepartureDate());
        assertEquals(LocalDate.of(2025, 2, 1), transport.getArrivalDate());
        assertEquals("Furniture", transport.getCargoDescription());
        assertEquals(800.0, transport.getCargoWeight());
        assertTrue(transport.isPaid());

        assertNull(transport.getClient());
        assertNull(transport.getEmployee());
        assertNull(transport.getVehicle());
        assertNull(transport.getCompany());
        assertNull(transport.getPrice());
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertNull(TransportMapper.toEntity(null));
    }
}
