package com.transportcompany.service;

import com.transportcompany.entity.Ticket;
import java.util.List;

public interface TicketService {

    Ticket getById(Integer id);

    List<Ticket> getAll();

    void create(Ticket ticket);

    void update(Ticket ticket);

    void delete(Integer id);
}