package com.transportcompany.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportDTO {

    private Long id;
    private Long clientId;
    private Long employeeId;
    private Long vehicleId;
    private Long companyId;
    private Long priceId;

    private String startPoint;
    private String endPoint;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

    private String cargoDescription;
    private Double cargoWeight;
    private boolean paid;
}
