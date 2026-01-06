package com.transportcompany.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String position;

    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal salary;

    @Positive
    private Long companyId;

}   // Трябва заплатата да е BigDecimal