package com.transportcompany.serviceImpl;

import com.transportcompany.dto.VehicleDTO;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.entity.Vehicle;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.repository.VehicleRepository;
import com.transportcompany.serviceImpl.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceImplTest {

    private VehicleServiceImpl service;
    private VehicleRepository vehicleRepoMock;
    private TransportCompanyRepository companyRepoMock;

    @BeforeEach
    void setUp() throws Exception {
        service = new VehicleServiceImpl();

        vehicleRepoMock = mock(VehicleRepository.class);
        companyRepoMock = mock(TransportCompanyRepository.class);

        injectMock(service, "vehicleRepo", vehicleRepoMock);
        injectMock(service, "companyRepo", companyRepoMock);
    }

    // ================= getById =================

    @Test
    void getById_shouldThrowIllegalArgument_whenIdInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> service.getById(0L));
    }

    @Test
    void getById_shouldThrowEntityNotFound_whenMissing() {
        when(vehicleRepoMock.findById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(1L));
    }

    @Test
    void getById_shouldReturnDTO_whenExists() {
        Vehicle v = new Vehicle();
        v.setId(1L);

        when(vehicleRepoMock.findById(1L)).thenReturn(v);

        VehicleDTO dto = service.getById(1L);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
    }

    // ================= getAll =================

    @Test
    void getAll_shouldReturnEmptyList_whenNoVehicles() {
        when(vehicleRepoMock.findAll()).thenReturn(List.of());

        List<VehicleDTO> result = service.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // ================= create =================

    @Test
    void create_shouldThrowEntityNotFound_whenCompanyMissing() {
        VehicleDTO dto = new VehicleDTO();
        dto.setCompanyId(1L);

        when(companyRepoMock.findById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> service.create(dto));
    }

    @Test
    void create_shouldSaveVehicle_whenCompanyExists() {
        VehicleDTO dto = new VehicleDTO();
        dto.setCompanyId(1L);
        dto.setRegistrationNumber("CB1234AB");
        dto.setCapacityTons(10.5);

        TransportCompany company = new TransportCompany();
        when(companyRepoMock.findById(1L)).thenReturn(company);

        VehicleDTO result = service.create(dto);

        verify(vehicleRepoMock).save(any(Vehicle.class));
        assertNotNull(result);
        assertEquals("CB1234AB", result.getRegistrationNumber());
    }

    // ================= update =================

    @Test
    void update_shouldThrowEntityNotFound_whenMissing() {
        when(vehicleRepoMock.findById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> service.update(1L, new VehicleDTO()));
    }

    @Test
    void update_shouldMerge_whenExists() {
        Vehicle existing = new Vehicle();
        when(vehicleRepoMock.findById(1L)).thenReturn(existing);

        VehicleDTO dto = new VehicleDTO();
        dto.setRegistrationNumber("NEW123");
        dto.setCapacityTons(15.5);

        VehicleDTO result = service.update(1L, dto);

        verify(vehicleRepoMock).merge(existing);
        assertEquals("NEW123", result.getRegistrationNumber());
    }

    // ================= delete =================

    @Test
    void delete_shouldThrowEntityNotFound_whenMissing() {
        when(vehicleRepoMock.findById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> service.delete(1L));
    }

    @Test
    void delete_shouldCallDelete_whenExists() {
        Vehicle v = new Vehicle();
        when(vehicleRepoMock.findById(1L)).thenReturn(v);

        service.delete(1L);

        verify(vehicleRepoMock).delete(v);
    }

    // ================= getByRegistrationNumber =================

    @Test
    void getByRegistrationNumber_shouldThrowEntityNotFound_whenMissing() {
        when(vehicleRepoMock.findByRegistrationNumber("ABC"))
                .thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> service.getByRegistrationNumber("ABC"));
    }

    @Test
    void getByRegistrationNumber_shouldReturnDTO_whenExists() {
        Vehicle v = new Vehicle();
        v.setRegistrationNumber("ABC");

        when(vehicleRepoMock.findByRegistrationNumber("ABC"))
                .thenReturn(v);

        VehicleDTO dto = service.getByRegistrationNumber("ABC");

        assertNotNull(dto);
        assertEquals("ABC", dto.getRegistrationNumber());
    }

    // ================= helper =================

    private void injectMock(Object target, String field, Object mock)
            throws Exception {
        Field f = target.getClass().getDeclaredField(field);
        f.setAccessible(true);
        f.set(target, mock);
    }
}