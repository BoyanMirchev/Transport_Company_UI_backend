package com.transportcompany.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;

    @Column(name = "ticket_name", nullable = false)
    private String name;

    // Many tickets use one price
    @ManyToOne
    @JoinColumn(name = "price_id", nullable = false)
    private Price price;

    @ManyToMany(mappedBy = "tickets")
    private Set<Client> clients;
}
