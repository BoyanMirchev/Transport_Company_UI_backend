package com.transportcompany.serviceImplTest.impl;

import com.transportcompany.dto.PriceDTO;
import com.transportcompany.entity.Price;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.PriceMapper;
import com.transportcompany.repository.PriceRepository;
import com.transportcompany.serviceImplTest.PriceService;

import java.math.BigDecimal;
import java.util.List;

public class PriceServiceImpl implements PriceService {

    private final PriceRepository repository = new PriceRepository();

    //Валидация
    @Override
    public PriceDTO getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid price ID");
        }

        Price price = repository.findById(id);
        if (price == null) {
            throw new EntityNotFoundException(
                    "Price with ID " + id + " not found"
            );
        }
        return PriceMapper.toDTO(price);
    }

    @Override
    public List<PriceDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(PriceMapper::toDTO)
                .toList();
    }

    @Override
    public PriceDTO create(PriceDTO dto) {
        validate(dto);

        Price price = PriceMapper.toEntity(dto);
        repository.save(price);

        return PriceMapper.toDTO(price);
    }

    @Override
    public PriceDTO update(Long id, PriceDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid price ID");
        }

        Price existing = repository.findById(id);
        if (existing == null) {
            throw new EntityNotFoundException("Price with ID " + id + " not found");
        }

        validate(dto);

        existing.setAmount(dto.getAmount());
        repository.merge(existing);

        return PriceMapper.toDTO(existing);
    }

    @Override
    public void delete(Long id) {
        Price price = repository.findById(id);
        if (price == null) {
            throw new EntityNotFoundException("Price with ID " + id + " not found");
        }
        repository.delete(price);
    }

    private void validate(PriceDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Price data is required");
        }

        if (dto.getAmount() == null) {
            throw new IllegalArgumentException("Price amount is required");
        }

        if (dto.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
