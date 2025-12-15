package com.transportcompany.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    private Integer companyId;
}   // Трябва заплатата да е BigDecimal