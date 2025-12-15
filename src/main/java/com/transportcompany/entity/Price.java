package com.transportcompany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private int id;

    @Column(nullable = false)
    private double amount;

    @OneToMany(mappedBy = "price")
    private Set<Transport> transports;

    @OneToMany(mappedBy = "price")
    private Set<Ticket> tickets;

    public Price(double amount) {
        this.amount = amount;
    }
}

