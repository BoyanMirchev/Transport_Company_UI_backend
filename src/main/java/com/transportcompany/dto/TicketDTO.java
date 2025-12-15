package com.transportcompany.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {

    private Integer id;
    private String name;
    private BigDecimal price;     // amount от Price
}             // BigDecimal
