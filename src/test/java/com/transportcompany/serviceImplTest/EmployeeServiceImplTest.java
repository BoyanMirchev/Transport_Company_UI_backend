package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.EmployeeDTO;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.serviceImplTest.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl service;
    private TransportCompanyRepository companyRepository;
    private TransportCompany company;

    @BeforeEach
    void setUp() {


        service = new EmployeeServiceImpl();
        companyRepository = new TransportCompanyRepository();

        company = new TransportCompany();
        company.setName("Test Company");
        companyRepository.save(company);
    }

    @Test
    void getById_shouldReturnEmployee_whenExists() {
        EmployeeDTO dto = createValidEmployeeDTO();

        EmployeeDTO created = service.create(dto);
        EmployeeDTO found = service.getById(created.getId());

        assertNotNull(found);
        assertEquals("Ivan", found.getFirstName());
        assertEquals("Petrov", found.getLastName());
    }

    @Test
    void getById_shouldThrow_whenEmployeeMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.getById(999L));
    }

    @Test
    void create_shouldPersistEmployee_whenValid() {
        EmployeeDTO dto = createValidEmployeeDTO();

        EmployeeDTO result = service.create(dto);

        assertNotNull(result.getId());
        assertEquals("Ivan", result.getFirstName());
        assertEquals(company.getId(), result.getCompanyId());
    }

    @Test
    void create_shouldThrow_whenCompanyMissing() {
        EmployeeDTO dto = createValidEmployeeDTO();
        dto.setCompanyId(999L);

        assertThrows(EntityNotFoundException.class,
                () -> service.create(dto));
    }

    @Test
    void create_shouldThrow_whenFirstNameInvalid() {
        EmployeeDTO dto = createValidEmployeeDTO();
        dto.setFirstName(" ");

        assertThrows(IllegalArgumentException.class,
                () -> service.create(dto));
    }

    @Test
    void update_shouldModifyEmployee_whenValid() {
        EmployeeDTO created = service.create(createValidEmployeeDTO());

        EmployeeDTO update = createValidEmployeeDTO();
        update.setFirstName("Georgi");
        update.setSalary(new BigDecimal("3200"));

        EmployeeDTO updated = service.update(created.getId(), update);

        assertEquals("Georgi", updated.getFirstName());
        assertEquals(new BigDecimal("3200"), updated.getSalary());
    }

    @Test
    void update_shouldThrow_whenEmployeeMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, createValidEmployeeDTO()));
    }

    @Test
    void delete_shouldRemoveEmployee() {
        EmployeeDTO created = service.create(createValidEmployeeDTO());

        service.delete(created.getId());

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(created.getId()));
    }

    @Test
    void delete_shouldThrow_whenEmployeeMissing() {
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(999L));
    }

    @Test
    void findByCompanyId_shouldReturnEmployeesFromCompany() {
        service.create(createValidEmployeeDTO());

        List<EmployeeDTO> result = service.findByCompanyId(company.getId());

        assertEquals(1, result.size());
    }

    // ===== helper =====

    private EmployeeDTO createValidEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFirstName("Ivan");
        dto.setLastName("Petrov");
        dto.setPosition("Driver");
        dto.setSalary(new BigDecimal("2500"));
        dto.setCompanyId(company.getId());
        return dto;
    }
}
