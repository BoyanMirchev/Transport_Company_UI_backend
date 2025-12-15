package com.transportcompany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;

    @Column(nullable = false)
    private String name;

    private String address;

    private String phone;

    private String email;

    @Column(name = "is_debtor")
    private boolean debtor;

    // Many clients belong to one company
    @ManyToOne
    @JoinColumn(name = "company_id")
    private TransportCompany company;

    // Client can have multiple tickets (M:N)
    @ManyToMany
    @JoinTable(
            name = "ticket_client",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id")
    )
    private Set<Ticket> tickets;

    // Client may have multiple transports
    @OneToMany(mappedBy = "client")
    private Set<Transport> transports;


}
 //                      ЛОГИКА НА ВРЪЗКИТЕ
//                 One Client -> One Company
//
//                 Един клиент работи с една транспортна компания.
//
//                 Many Clients -> Many Tickets
//
//                 Клиент може да купи много билети (автобусни).
//
//                 Един билет може да бъде купен от много клиенти.
//                 Затова: @ManyToMany.
//
//                 One Client -> Many Transports
//
//                 Клиент може да направи много заявки за транспорт на товари.