package com.transportcompany.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {

    private Integer id;
    private String registrationNumber;
    private String type;
    private Double capacityTons;

    private Integer companyId;
}
