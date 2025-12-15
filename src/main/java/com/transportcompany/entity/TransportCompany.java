package com.transportcompany.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "transport_company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String address;

    private String phone;

    private double revenue;

    // Relationships
    @OneToMany(mappedBy = "company")
    private Set<Client> clients;

    @OneToMany(mappedBy = "company")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "company")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "company")
    private Set<Transport> transports;
}
