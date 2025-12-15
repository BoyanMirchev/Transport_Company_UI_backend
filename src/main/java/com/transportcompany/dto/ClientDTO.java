package com.transportcompany.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;

    private boolean debtor;
    private Integer companyId;
}
