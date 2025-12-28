package com.transportcompany.serviceImplTest.impl;

import com.transportcompany.dto.TicketDTO;
import com.transportcompany.entity.Price;
import com.transportcompany.entity.Ticket;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.TicketMapper;
import com.transportcompany.repository.PriceRepository;
import com.transportcompany.repository.TicketRepository;
import com.transportcompany.serviceImplTest.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository = new TicketRepository();
    private final PriceRepository priceRepository = new PriceRepository();

     //Валидация
    @Override
    public TicketDTO getById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ticket ID");
        }

        Ticket ticket = repository.findById(id);
        if (ticket == null) {
            throw new EntityNotFoundException(
                    "Ticket with ID " + id + " not found"
            );
        }
        return TicketMapper.toDTO(ticket);
    }

    @Override
    public List<TicketDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(TicketMapper::toDTO)
                .toList();
    }

    @Override
    public TicketDTO create(TicketDTO dto) {
        validate(dto);

        // търсим Price по amount, не по ID
        Price price = priceRepository.findByAmount(dto.getPrice());
        if (price == null) {
            price = new Price();
            price.setAmount(dto.getPrice());
            priceRepository.save(price);
        }

        Ticket ticket = new Ticket();
        ticket.setName(dto.getName());
        ticket.setPrice(price);

        repository.save(ticket);
        return TicketMapper.toDTO(ticket);
    }

    @Override
    public TicketDTO update(Long id, TicketDTO dto) {
        Ticket existing = repository.findById(id);
        if (existing == null) {
            throw new EntityNotFoundException("Ticket with ID " + id + " not found");
        }

        validate(dto);

        Price price = priceRepository.findByAmount(dto.getPrice());
        if (price == null) {
            price = new Price();
            price.setAmount(dto.getPrice());
            priceRepository.save(price);
        }

        existing.setName(dto.getName());
        existing.setPrice(price);

        repository.merge(existing);
        return TicketMapper.toDTO(existing);
    }

    @Override
    public void delete(Long id) {
        Ticket ticket = repository.findById(id);
        if (ticket == null) {
            throw new EntityNotFoundException("Ticket with ID " + id + " not found");
        }
        repository.delete(ticket);
    }

    private void validate(TicketDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Ticket data is required");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Ticket name cannot be empty");
        }
        if (dto.getPrice() == null || dto.getPrice().signum() < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
    }
}
