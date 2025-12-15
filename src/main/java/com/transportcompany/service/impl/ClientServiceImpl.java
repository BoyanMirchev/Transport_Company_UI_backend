package com.transportcompany.service.impl;

import com.transportcompany.dto.ClientDTO;
import com.transportcompany.entity.Client;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.ClientMapper;
import com.transportcompany.repository.ClientRepository;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.service.ClientService;
import com.transportcompany.validation.ClientValidator;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository repo = new ClientRepository();
    private final TransportCompanyRepository companyRepo = new TransportCompanyRepository();

    @Override
    public ClientDTO getById(Integer id) {
        Client c = repo.findById(id);
        if (c == null) throw new EntityNotFoundException("Client with ID " + id + " not found");
        return ClientMapper.toDTO(c);
    }

    @Override
    public List<ClientDTO> getAll() {
        return repo.findAll().stream().map(ClientMapper::toDTO).toList();
    }

    @Override
    public ClientDTO create(ClientDTO dto) {
        ClientValidator.validateForCreate(dto);

        Client entity = ClientMapper.toEntity(dto);
        TransportCompany company = companyRepo.findById(dto.getCompanyId());
        if (company == null) throw new EntityNotFoundException("Company with ID " + dto.getCompanyId() + " not found");

        entity.setCompany(company);
        repo.save(entity);
        return ClientMapper.toDTO(entity);
    }

    @Override
    public ClientDTO update(ClientDTO dto) {
        if (dto == null || dto.getId() == null) throw new com.transportcompany.exceptions.InvalidDataException("Client id required for update");
        // ensure exists
        repo.findById(dto.getId()); // can be null; check properly:
        Client existing = repo.findById(dto.getId());
        if (existing == null) throw new EntityNotFoundException("Client with ID " + dto.getId() + " not found");

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());


        if (dto.getCompanyId() != null) {
            TransportCompany company = companyRepo.findById(dto.getCompanyId());
            if (company == null) throw new EntityNotFoundException("Company with ID " + dto.getCompanyId() + " not found");
            existing.setCompany(company);
        }

        repo.merge(existing);
        return ClientMapper.toDTO(existing);
    }

    @Override
    public void delete(Integer id) {
        Client c = repo.findById(id);
        if (c == null) throw new EntityNotFoundException("Client with ID " + id + " not found");
        repo.delete(c);
    }

    @Override
    public ClientDTO findByEmail(String email) {
        Client c = repo.findByEmail(email);
        if (c == null) throw new EntityNotFoundException("Client with email " + email + " not found");
        return ClientMapper.toDTO(c);
    }
}
