package com.transportcompany.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDTO {

    private int id;
    private BigDecimal amount; // BigDecimal
}
