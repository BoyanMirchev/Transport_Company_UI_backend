package com.transportcompany.serviceImplTest.impl;

import com.transportcompany.dto.TransportCompanyDTO;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.TransportCompanyMapper;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.serviceImplTest.TransportCompanyService;

import java.util.List;

public class TransportCompanyServiceImpl implements TransportCompanyService {

    private final TransportCompanyRepository repository = new TransportCompanyRepository();

        //Валидация
    @Override
    public TransportCompanyDTO getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid company ID");
        }

        TransportCompany entity = repository.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException(
                    "Company with ID " + id + " not found"
            );
        }
        return TransportCompanyMapper.toDTO(entity);
    }

    @Override
    public List<TransportCompanyDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(TransportCompanyMapper::toDTO)
                .toList();
    }

    @Override
    public TransportCompanyDTO create(TransportCompanyDTO dto) {
        if (dto == null || dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Company name is required");
        }

        TransportCompany entity = TransportCompanyMapper.toEntity(dto);
        repository.save(entity);
        return TransportCompanyMapper.toDTO(entity);
    }

    @Override
    public TransportCompanyDTO update(TransportCompanyDTO dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Company ID is required");
        }

        TransportCompany existing = repository.findById(dto.getId());
        if (existing == null) {
            throw new EntityNotFoundException("Company with ID " + dto.getId() + " not found");
        }

        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setPhone(dto.getPhone());
        existing.setRevenue(dto.getRevenue());

        repository.merge(existing);
        return TransportCompanyMapper.toDTO(existing);
    }

    @Override
    public void delete(Long id) {
        TransportCompany entity = repository.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException("Company with ID " + id + " not found");
        }
        repository.delete(entity);
    }
}

