package com.transportcompany.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportDTO {

    private int id;
    private String startPoint;
    private String endPoint;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

    private String cargoDescription;
    private Double cargoWeight;
    private boolean paid;

    private int clientId;
    private int employeeId;
    private int vehicleId;
    private int companyId;
    private BigDecimal priceId;
}