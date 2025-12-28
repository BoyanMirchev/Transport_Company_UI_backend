package com.transportcompany.serviceImplTest;

import com.transportcompany.config.HibernateTx;
import com.transportcompany.dto.VehicleDTO;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.entity.Vehicle;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.serviceImplTest.VehicleService;
import com.transportcompany.serviceImplTest.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleServiceImplTest {

    private VehicleService service;
    private TransportCompany company;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        service = new VehicleServiceImpl();

        HibernateTx.inTxVoid(session -> {
            company = new TransportCompany();
            company.setName("Test Company");
            session.persist(company);

            vehicle = new Vehicle();
            vehicle.setRegistrationNumber("CB1234AA");
            vehicle.setCapacityTons(10.0);
            vehicle.setCompany(company);
            session.persist(vehicle);
        });
    }

    // GET BY ID

    @Test
    void getById_shouldReturnVehicle_whenExists() {
        VehicleDTO dto = service.getById(vehicle.getId());

        assertNotNull(dto);
        assertEquals("CB1234AA", dto.getRegistrationNumber());
        assertEquals(10.0, dto.getCapacityTons());
    }

    @Test
    void getById_shouldThrow_whenMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getById(999L));
    }

    // GET ALL

    @Test
    void getAll_shouldReturnList() {
        List<VehicleDTO> list = service.getAll();

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    //  CREATE

    @Test
    void create_shouldPersistVehicle_whenCompanyExists() {
        VehicleDTO dto = new VehicleDTO();
        dto.setRegistrationNumber("CB9999BB");
        dto.setCapacityTons(5.0);
        dto.setCompanyId(company.getId());

        VehicleDTO created = service.create(dto);

        assertNotNull(created.getId());
        assertEquals("CB9999BB", created.getRegistrationNumber());
        assertEquals(5.0, created.getCapacityTons());
    }

    @Test
    void create_shouldThrow_whenCompanyMissing() {
        VehicleDTO dto = new VehicleDTO();
        dto.setRegistrationNumber("CB0000XX");
        dto.setCapacityTons(5.0);
        dto.setCompanyId(999L);

        assertThrows(EntityNotFoundException.class,
                () -> service.create(dto));
    }

    //  UPDATE

    @Test
    void update_shouldModifyVehicle_whenValid() {
        VehicleDTO dto = new VehicleDTO();
        dto.setRegistrationNumber("UPDATED123");
        dto.setCapacityTons(20.0);

        VehicleDTO updated = service.update(vehicle.getId(), dto);

        assertEquals("UPDATED123", updated.getRegistrationNumber());
        assertEquals(20.0, updated.getCapacityTons());
    }

    @Test
    void update_shouldThrow_whenVehicleMissing() {
        VehicleDTO dto = new VehicleDTO();
        dto.setRegistrationNumber("FAIL");

        assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, dto));
    }

    //  DELETE

    @Test
    void delete_shouldRemoveVehicle() {
        service.delete(vehicle.getId());

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(vehicle.getId()));
    }

    @Test
    void delete_shouldThrow_whenMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(999L));
    }

    //  GET BY REGISTRATION

    @Test
    void getByRegistrationNumber_shouldReturnVehicle_whenExists() {
        VehicleDTO dto = service.getByRegistrationNumber("CB1234AA");

        assertNotNull(dto);
        assertEquals(vehicle.getId(), dto.getId());
    }

    @Test
    void getByRegistrationNumber_shouldThrow_whenMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getByRegistrationNumber("NOPE"));
    }
}
