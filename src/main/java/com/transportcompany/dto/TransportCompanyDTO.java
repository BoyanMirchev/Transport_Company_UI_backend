package com.transportcompany.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportCompanyDTO {

    private Integer id;
    private String name;
    private String address;
    private String phone;
    private BigDecimal revenue;
}
