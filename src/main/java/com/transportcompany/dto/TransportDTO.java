package com.transportcompany.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportDTO {
    private Integer id;
    private String startPoint;
    private String endPoint;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

    private String cargoDescription;
    private Double cargoWeight;
    private boolean paid;

    private Integer clientId;
    private Integer employeeId;
    private Integer vehicleId;
    private Integer companyId;
    private Integer priceId;
}