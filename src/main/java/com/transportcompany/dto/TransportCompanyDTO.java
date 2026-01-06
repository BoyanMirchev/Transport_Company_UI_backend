package com.transportcompany.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportCompanyDTO {

    private Long id;
    @NotBlank
    @Size(min = 2, max = 150)
    private String name;

    @NotBlank
    @Size(min = 5, max = 255)
    private String address;

    @NotBlank
    @Pattern(regexp = "^[+]?\\d{8,15}$")
    private String phone;

    @PositiveOrZero
    @Digits(integer = 15, fraction = 2)
    private BigDecimal revenue;
}
