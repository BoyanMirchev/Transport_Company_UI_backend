package com.transportcompany.dto;

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
    private String name;
    private BigDecimal price;     // amount от Price


}             // BigDecimal
