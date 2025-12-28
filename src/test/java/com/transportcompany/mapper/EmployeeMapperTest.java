package com.transportcompany.mapper;

import com.transportcompany.dto.EmployeeDTO;
import com.transportcompany.entity.Employee;
import com.transportcompany.entity.TransportCompany;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    @Test
    void toDTO_shouldMapAllFields_whenCompanyExists() {
        TransportCompany company = new TransportCompany();
        company.setId(3L);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Ivan");
        employee.setLastName("Petrov");
        employee.setSalary(new BigDecimal("2500.00"));
        employee.setCompany(company);

        EmployeeDTO dto = EmployeeMapper.toDTO(employee);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Ivan", dto.getFirstName());
        assertEquals("Petrov", dto.getLastName());
        assertEquals(new BigDecimal("2500.00"), dto.getSalary());
        assertEquals(3L, dto.getCompanyId());
    }

    @Test
    void toDTO_shouldSetCompanyIdNull_whenCompanyMissing() {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setFirstName("Maria");
        employee.setLastName("Ivanova");
        employee.setSalary(new BigDecimal("1800.00"));
        employee.setCompany(null);

        EmployeeDTO dto = EmployeeMapper.toDTO(employee);

        assertNotNull(dto);
        assertNull(dto.getCompanyId());
    }

    @Test
    void toDTO_shouldReturnNull_whenEntityIsNull() {
        assertNull(EmployeeMapper.toDTO(null));
    }

    @Test
    void toEntity_shouldMapFields_withoutId() {
        EmployeeDTO dto = EmployeeDTO.builder()
                .firstName("Georgi")
                .lastName("Dimitrov")
                .salary(new BigDecimal("3000.00"))
                .build();

        Employee employee = EmployeeMapper.toEntity(dto);

        assertNotNull(employee);
        assertNull(employee.getId());
        assertEquals("Georgi", employee.getFirstName());
        assertEquals("Dimitrov", employee.getLastName());
        assertEquals(new BigDecimal("3000.00"), employee.getSalary());
    }

    @Test
    void toEntity_shouldSetId_whenIdProvided() {
        EmployeeDTO dto = EmployeeDTO.builder()
                .id(7L)
                .firstName("Petya")
                .lastName("Koleva")
                .salary(new BigDecimal("2200.00"))
                .build();

        Employee employee = EmployeeMapper.toEntity(dto);

        assertNotNull(employee);
        assertEquals(7L, employee.getId());
        assertEquals("Petya", employee.getFirstName());
        assertEquals("Koleva", employee.getLastName());
        assertEquals(new BigDecimal("2200.00"), employee.getSalary());
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertNull(EmployeeMapper.toEntity(null));
    }
}
