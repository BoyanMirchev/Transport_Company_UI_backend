package com.transportcompany.service.impl;

import com.transportcompany.entity.TransportCompany;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.service.TransportCompanyService;

import java.util.List;

public class TransportCompanyServiceImpl implements TransportCompanyService {

    private final TransportCompanyRepository repository;

    public TransportCompanyServiceImpl() {
        this.repository = new TransportCompanyRepository();
    }

    @Override
    public TransportCompany getById(Integer id) {
        TransportCompany c = repository.findById(id);
        if (c == null)
            throw new EntityNotFoundException("Company with ID " + id + " not found");
        return c;
    }

    @Override
    public List<TransportCompany> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(TransportCompany company) {
        repository.save(company);
    }

    @Override
    public void update(TransportCompany company) {
        getById(company.getId());
        repository.merge(company);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(getById(id));
    }
}
