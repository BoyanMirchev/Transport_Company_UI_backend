package com.transportcompany.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    private Long companyId;
}   // Трябва заплатата да е BigDecimal