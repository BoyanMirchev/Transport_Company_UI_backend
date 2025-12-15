package com.transportcompany.service.impl;

import com.transportcompany.entity.Price;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.PriceRepository;
import com.transportcompany.service.PriceService;

import java.math.BigDecimal;
import java.util.List;

public class PriceServiceImpl implements PriceService {

    private final PriceRepository repository = new PriceRepository();

    @Override
    public Price getById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid price ID");
        }

        Price price = repository.findById(id);
        if (price == null) {
            throw new EntityNotFoundException("Price with ID " + id + " not found");
        }
        return price;
    }

    @Override
    public List<Price> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Price price) {

        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        if (price.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        repository.save(price);
    }

    @Override
    public void update(Price price) {

        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        // проверка дали съществува
        getById(price.getId());

        if (price.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        repository.merge(price);
    }

    @Override
    public void delete(Integer id) {
        Price p = getById(id); // ще хвърли грешка ако ID го няма
        repository.delete(p);
    }
}
