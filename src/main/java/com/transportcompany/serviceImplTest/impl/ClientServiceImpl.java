package com.transportcompany.serviceImplTest.impl;

import com.transportcompany.dto.ClientDTO;
import com.transportcompany.entity.Client;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.ClientMapper;
import com.transportcompany.repository.ClientRepository;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.serviceImplTest.ClientService;
import com.transportcompany.validation.ClientValidator;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository repo = new ClientRepository();
    private final TransportCompanyRepository companyRepo = new TransportCompanyRepository();

    //Валидация
    @Override
    public ClientDTO getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid client ID");
        }

        Client client = repo.findById(id);
        if (client == null) {
            throw new EntityNotFoundException(
                    "Client with ID " + id + " not found"
            );
        }
        return ClientMapper.toDTO(client);
    }

    @Override
    public List<ClientDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .toList();
    }

    @Override
    public ClientDTO create(ClientDTO dto) {
        ClientValidator.validateForCreate(dto);

        TransportCompany company = companyRepo.findById(dto.getCompanyId());
        if (company == null) {
            throw new EntityNotFoundException(
                    "Company with ID " + dto.getCompanyId() + " not found"
            );
        }

        Client entity = ClientMapper.toEntity(dto);
        entity.setCompany(company);

        repo.save(entity);
        return ClientMapper.toDTO(entity);
    }

    @Override
    public ClientDTO update(Long id, ClientDTO dto) {
        Client existing = repo.findById(id);
        if (existing == null) {
            throw new EntityNotFoundException("Client with ID " + id + " not found");
        }

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        if (dto.getCompanyId() != null) {
            TransportCompany company = companyRepo.findById(dto.getCompanyId());
            if (company == null) {
                throw new EntityNotFoundException(
                        "Company with ID " + dto.getCompanyId() + " not found"
                );
            }
            existing.setCompany(company);
        }

        repo.merge(existing);
        return ClientMapper.toDTO(existing);
    }

    @Override
    public void delete(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid client ID");
        }

        Client client = repo.findById(id);
        if (client == null) {
            throw new EntityNotFoundException(
                    "Client with ID " + id + " not found"
            );
        }
        repo.delete(client);
    }

    @Override
    public ClientDTO findByEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        Client client = repo.findByEmail(email);
        if (client == null) {
            throw new EntityNotFoundException(
                    "Client with email " + email + " not found"
            );
        }
        return ClientMapper.toDTO(client);
    }
}