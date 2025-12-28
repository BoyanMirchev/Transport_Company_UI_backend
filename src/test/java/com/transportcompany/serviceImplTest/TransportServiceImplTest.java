package com.transportcompany.serviceImplTest;

import com.transportcompany.config.HibernateTestUtil;
import com.transportcompany.config.HibernateTx;
import com.transportcompany.dto.TransportDTO;
import com.transportcompany.entity.*;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.*;
import com.transportcompany.serviceImplTest.impl.TransportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportServiceImplTest {

    private TransportServiceImpl service;

    private TransportCompanyRepository companyRepo;
    private ClientRepository clientRepo;
    private EmployeeRepository employeeRepo;
    private VehicleRepository vehicleRepo;
    private PriceRepository priceRepo;

    private TransportCompany company;
    private Client client;
    private Employee employee;
    private Vehicle vehicle;
    private Price price;

    @BeforeEach
    void setUp() {
        HibernateTx.inTxVoid(session -> {

            company = new TransportCompany();
            company.setName("Test Company");
            session.persist(company);

            client = new Client();
            client.setName("Ivan");
            client.setCompany(company);
            session.persist(client);

            employee = new Employee();
            employee.setCompany(company);
            session.persist(employee);

            vehicle = new Vehicle();
            vehicle.setCompany(company);
            session.persist(vehicle);

            price = new Price();
            price.setAmount(new BigDecimal("100"));
            session.persist(price);
        });

        service = new TransportServiceImpl();
    }
    @Test
    void create_shouldPersistTransport_whenAllDependenciesExist() {
        TransportDTO dto = validTransportDTO();

        TransportDTO created = service.create(dto);

        assertNotNull(created.getId());
        assertEquals("Sofia", created.getStartPoint());
        assertEquals(company.getId(), created.getCompanyId());
        assertTrue(created.isPaid());
    }

    @Test
    void create_shouldThrow_whenDtoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.create(null));
    }

    @Test
    void create_shouldThrow_whenCompanyMissing() {
        TransportDTO dto = validTransportDTO();
        dto.setCompanyId(999L);

        assertThrows(EntityNotFoundException.class,
                () -> service.create(dto));
    }

    @Test
    void getById_shouldReturnTransport_whenExists() {
        TransportDTO created = service.create(validTransportDTO());

        TransportDTO found = service.getById(created.getId());

        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
    }

    @Test
    void getById_shouldThrow_whenMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getById(999L));
    }

    @Test
    void getAll_shouldReturnAllTransports() {
        service.create(validTransportDTO());

        TransportDTO second = validTransportDTO();
        second.setPaid(false);
        service.create(second);

        List<TransportDTO> all = service.getAll();

        assertEquals(2, all.size());
    }

    @Test
    void update_shouldModifyTransport_whenValid() {
        TransportDTO created = service.create(validTransportDTO());

        created.setEndPoint("Plovdiv");
        created.setPaid(false);

        TransportDTO updated = service.update(created);

        assertEquals("Plovdiv", updated.getEndPoint());
        assertFalse(updated.isPaid());
    }

    @Test
    void update_shouldThrow_whenIdMissing() {
        TransportDTO dto = validTransportDTO();
        dto.setId(null);

        assertThrows(IllegalArgumentException.class,
                () -> service.update(dto));
    }

    @Test
    void update_shouldThrow_whenTransportMissing() {
        TransportDTO dto = validTransportDTO();
        dto.setId(999L);

        assertThrows(EntityNotFoundException.class,
                () -> service.update(dto));
    }

    @Test
    void delete_shouldRemoveTransport() {
        TransportDTO created = service.create(validTransportDTO());

        service.delete(created.getId());

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(created.getId()));
    }

    @Test
    void delete_shouldThrow_whenMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(999L));
    }

    @Test
    void getPaidByCompany_shouldReturnOnlyPaid() {
        TransportDTO paid = validTransportDTO();
        paid.setPaid(true);
        service.create(paid);

        TransportDTO unpaid = validTransportDTO();
        unpaid.setPaid(false);
        service.create(unpaid);

        List<TransportDTO> result =
                service.getPaidByCompany(company.getId());

        assertEquals(1, result.size());
        assertTrue(result.get(0).isPaid());
    }

    // ===== helper =====

    private TransportDTO validTransportDTO() {
        TransportDTO dto = new TransportDTO();
        dto.setStartPoint("Sofia");
        dto.setEndPoint("Varna");
        dto.setDepartureDate(LocalDate.now());
        dto.setArrivalDate(LocalDate.now().plusDays(1));
        dto.setPaid(true);

        dto.setCompanyId(company.getId());
        dto.setClientId(client.getId());
        dto.setEmployeeId(employee.getId());
        dto.setVehicleId(vehicle.getId());
        dto.setPriceId(price.getId());

        return dto;
    }
}
