package com.transportcompany.repository;

import com.transportcompany.entity.Ticket;

public class TicketRepository extends BaseRepository<Ticket> {

    public TicketRepository() {
        super(Ticket.class);
    }
}