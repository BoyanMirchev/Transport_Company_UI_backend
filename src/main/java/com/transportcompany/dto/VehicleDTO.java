package com.transportcompany.dto;

import com.transportcompany.entity.VehicleType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {

    private Long id;
    @NotBlank
    @Size(min = 5, max = 15)
    private String registrationNumber;

    @NotNull
    private VehicleType type;

    @NotNull
    @Positive
    @DecimalMax("100")
    private Double capacityTons;

    @NotNull
    @Positive
    private Long companyId;

}
