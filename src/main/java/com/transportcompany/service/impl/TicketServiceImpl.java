package com.transportcompany.service.impl;

import com.transportcompany.entity.Price;
import com.transportcompany.entity.Ticket;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.TicketRepository;
import com.transportcompany.service.PriceService;
import com.transportcompany.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository = new TicketRepository();
    private final PriceService priceService = new PriceServiceImpl();

    @Override
    public Ticket getById(Integer id) {
        Ticket ticket = repository.findById(id);
        if (ticket == null)
            throw new EntityNotFoundException("Ticket with ID " + id + " not found");
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Ticket ticket) {
        if (ticket.getName() == null || ticket.getName().isBlank())
            throw new IllegalArgumentException("Ticket name cannot be empty");

        // Валидация на Price през PriceService
        Price p = priceService.getById(ticket.getPrice().getId());
        ticket.setPrice(p);

        repository.save(ticket);
    }

    @Override
    public void update(Ticket ticket) {
        getById(ticket.getId());
        repository.merge(ticket);
    }

    @Override
    public void delete(Integer id) {
        Ticket t = getById(id);
        repository.delete(t);
    }
}