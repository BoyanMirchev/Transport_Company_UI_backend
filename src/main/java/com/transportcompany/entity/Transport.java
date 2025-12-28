package com.transportcompany.entity;

import com.transportcompany.exceptions.InvalidDataException;
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
    private Long id;

    @Column(name = "start_point", nullable = false)
    private String startPoint;

    @Column(name = "end_point", nullable = false)
    private String endPoint;

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "cargo_description")
    private String cargoDescription;

    @Column(name = "cargo_weight")
    private Double cargoWeight;

    @Column(name = "is_paid", nullable = false)
    private boolean paid;

    // ========= RELATIONS =========

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private TransportCompany company;

    @ManyToOne(optional = false)
    @JoinColumn(name = "price_id", nullable = false)
    private Price price;

    // ========= DOMAIN INVARIANTS =========

    @PrePersist
    @PreUpdate
    private void validate() {

        if (startPoint == null || startPoint.isBlank())
            throw new InvalidDataException("Start point is required");

        if (endPoint == null || endPoint.isBlank())
            throw new InvalidDataException("End point is required");

        if (departureDate == null)
            throw new InvalidDataException("Departure date is required");

        if (arrivalDate != null && arrivalDate.isBefore(departureDate))
            throw new InvalidDataException("Arrival date cannot be before departure date");

        if (cargoWeight != null && cargoWeight < 0)
            throw new InvalidDataException("Cargo weight cannot be negative");
    }
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

