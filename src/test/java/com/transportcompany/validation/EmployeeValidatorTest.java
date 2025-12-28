package com.transportcompany.validation;

import com.transportcompany.dto.EmployeeDTO;
import com.transportcompany.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeValidatorTest {

    // ===================== POSITIVE =====================

    @Test
    void validateForCreate_shouldPass_whenDataIsValid() {
        EmployeeDTO dto = validEmployeeDTO();

        assertDoesNotThrow(() ->
                EmployeeValidator.validateForCreate(dto));
    }

    // ===================== DTO =====================

    @Test
    void validateForCreate_shouldThrow_whenDtoIsNull() {
        assertThrows(InvalidDataException.class,
                () -> EmployeeValidator.validateForCreate(null));
    }

    // ===================== FIRST NAME =====================

    @Test
    void validateForCreate_shouldThrow_whenFirstNameNull() {
        EmployeeDTO dto = validEmployeeDTO();
        dto.setFirstName(null);

        assertThrows(InvalidDataException.class,
                () -> EmployeeValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenFirstNameBlank() {
        EmployeeDTO dto = validEmployeeDTO();
        dto.setFirstName("   ");

        assertThrows(InvalidDataException.class,
                () -> EmployeeValidator.validateForCreate(dto));
    }

    // ===================== LAST NAME =====================

    @Test
    void validateForCreate_shouldThrow_whenLastNameNull() {
        EmployeeDTO dto = validEmployeeDTO();
        dto.setLastName(null);

        assertThrows(InvalidDataException.class,
                () -> EmployeeValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenLastNameBlank() {
        EmployeeDTO dto = validEmployeeDTO();
        dto.setLastName("");

        assertThrows(InvalidDataException.class,
                () -> EmployeeValidator.validateForCreate(dto));
    }

    // ===================== COMPANY ID =====================

    @Test
    void validateForCreate_shouldThrow_whenCompanyIdNull() {
        EmployeeDTO dto = validEmployeeDTO();
        dto.setCompanyId(null);

        assertThrows(InvalidDataException.class,
                () -> EmployeeValidator.validateForCreate(dto));
    }

    // ===================== SALARY =====================

    @Test
    void validateForCreate_shouldThrow_whenSalaryNull() {
        EmployeeDTO dto = validEmployeeDTO();
        dto.setSalary(null);

        assertThrows(InvalidDataException.class,
                () -> EmployeeValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenSalaryNegative() {
        EmployeeDTO dto = validEmployeeDTO();
        dto.setSalary(new BigDecimal("-1"));

        assertThrows(InvalidDataException.class,
                () -> EmployeeValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldPass_whenSalaryZero() {
        EmployeeDTO dto = validEmployeeDTO();
        dto.setSalary(BigDecimal.ZERO);

        assertDoesNotThrow(() ->
                EmployeeValidator.validateForCreate(dto));
    }

    // ===================== HELPER =====================

    private EmployeeDTO validEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFirstName("Ivan");
        dto.setLastName("Petrov");
        dto.setCompanyId(1L);
        dto.setSalary(new BigDecimal("1500"));
        return dto;
    }
}
