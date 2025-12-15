package com.transportcompany.validation;

import com.transportcompany.dto.EmployeeDTO;
import java.math.BigDecimal;

public final class EmployeeValidator {
    private EmployeeValidator(){}
    public static void validateForCreate(EmployeeDTO dto) {
        Validation.notNull(dto, "EmployeeDTO is null");
        Validation.notBlank(dto.getFirstName(), "First name required");
        Validation.notBlank(dto.getLastName(), "Last name required");
        Validation.notNull(dto.getCompanyId(), "companyId required");
        Validation.notNull(dto.getSalary(), "Salary required");
        Validation.require(dto.getSalary().compareTo(BigDecimal.ZERO) >= 0, "Salary cannot be negative");
    }
}