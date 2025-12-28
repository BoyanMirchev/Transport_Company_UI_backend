package com.transportcompany.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    @Column(name = "capacity_tons")
    private Double capacityTons;

    // Many vehicles belong to ONE company
    @ManyToOne
    @JoinColumn(name = "company_id")
    private TransportCompany company;

    // One vehicle → many transports
    @OneToMany(mappedBy = "vehicle")
    private Set<Transport> transports;

}
//                     ЛОГИКА НА ВРЪЗКИТЕ

//                  Vehicle → Company = ManyToOne
//                  Vehicle → Transport = OneToMany
//                  Има уникален registration_number
//                  Пълна съвместимост с SQL модела в MySQL workbench