package com.transportcompany.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TicketDTO {

    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;


}             // BigDecimal
