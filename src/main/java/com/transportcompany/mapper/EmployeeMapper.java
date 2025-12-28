package com.transportcompany.mapper;

import com.transportcompany.dto.EmployeeDTO;
import com.transportcompany.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee e) {
        if (e == null) return null;

        return EmployeeDTO.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .salary(e.getSalary()) // BigDecimal -> BigDecimal (OK)
                .companyId(
                        e.getCompany() != null ? e.getCompany().getId() : null
                )
                .build();
    }

    public static Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        Employee e = new Employee();

        // ️ ID се сетва САМО ако не е null важно за create vs update
        if (dto.getId() != null) {
            e.setId(dto.getId());
        }

        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setSalary(dto.getSalary());

        //  Company НЕ се сетва тук
        // ЩЕ се сетне в Service слоя след fetch по companyId

        return e;
    }
}
