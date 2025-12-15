package com.transportcompany.service.impl;

import com.transportcompany.entity.Client;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.ClientRepository;
import com.transportcompany.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository = new ClientRepository();

    @Override
    public Client getById(Integer id) {
        Client client = repository.findById(id);
        if (client == null) {
            throw new EntityNotFoundException("Client with ID " + id + " not found");
        }
        return client;
    }

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Client client) {

        // Валидация
        if (client.getName() == null || client.getName().isBlank()) {
            throw new IllegalArgumentException("Client name cannot be empty");
        }

        if (client.getEmail() != null && client.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }

        repository.save(client);
    }

    @Override
    public void update(Client client) {
        // Проверка дали съществува
        getById(client.getId());

        repository.update(client);
    }

    @Override
    public void delete(Integer id) {
        Client client = getById(id);
        repository.delete(client);
    }

    @Override
    public Client findByEmail(String email) {
        List<Client> allClients = repository.findAll();

        return allClients.stream()
                .filter(c -> email.equalsIgnoreCase(c.getEmail()))
                .findFirst()
                .orElseThrow(() ->
                        new EntityNotFoundException("Client with email " + email + " not found"));
    }
}