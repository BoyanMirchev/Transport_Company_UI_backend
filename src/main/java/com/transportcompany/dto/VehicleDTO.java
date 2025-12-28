package com.transportcompany.dto;

import com.transportcompany.entity.VehicleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {

    private Long id;
    private String registrationNumber;
    private VehicleType type;
    private Double capacityTons;
    private Long companyId;

}
