package com.transportcompany.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "transport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transport_id")
    private int id;

    @Column(name = "start_point")
    private String startPoint;

    @Column(name = "end_point")
    private String endPoint;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "cargo_description")
    private String cargoDescription;

    @Column(name = "cargo_weight")
    private Double cargoWeight;

    @Column(name = "is_paid")
    private boolean paid;

    // Many transports -> One vehicle
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // Many transports -> One employee (driver)
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Many transports -> One client
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Many transports -> One company
    @ManyToOne
    @JoinColumn(name = "company_id")
    private TransportCompany company;

    // Many transports -> One price reference
    @ManyToOne
    @JoinColumn(name = "price_id")
    private Price price;
}
//                     ЛОГИКА НА ВРЪЗКИТЕ
//              @ManyToOne — правилната релация за всички FK
//              -> Всеки transport има 1 vehicle
//              ->  1 employee
//              ->  1 client
//              ->  1 company
//              ->  1 price
//
//                     LocalDate вместо Date
//
//              ->  Hibernate го обработва перфектно.
//              ->  С по-нисък риск от timezone проблеми.
//
//                       paid (boolean)
//                 -> Съвпада с is_paid BOOLEAN.

