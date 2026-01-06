package com.transportcompany.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportDTO {

    private Long id;
    @NotNull
    @Positive
    private Long clientId;

    @NotNull @Positive
    private Long employeeId;

    @NotNull @Positive
    private Long vehicleId;

    @NotNull @Positive
    private Long companyId;

    @NotNull @Positive
    private Long priceId;

    @NotBlank
    @Size(min = 2, max = 255)
    private String startPoint;

    @NotBlank
    @Size(min = 2, max = 255)
    private String endPoint;

    @NotNull
    @FutureOrPresent
    private LocalDate departureDate;

    @NotNull
    @Future
    private LocalDate arrivalDate;

    @Size(max = 500)
    private String cargoDescription;

    @NotNull
    @Positive
    private Double cargoWeight;
    private boolean paid;
}
