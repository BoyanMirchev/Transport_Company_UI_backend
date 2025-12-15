package com.transportcompany.service.impl;

import com.transportcompany.entity.TransportCompany;
import com.transportcompany.entity.Vehicle;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.VehicleRepository;
import com.transportcompany.service.TransportCompanyService;
import com.transportcompany.service.VehicleService;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;
    private final TransportCompanyService companyService;

    public VehicleServiceImpl() {
        this.repository = new VehicleRepository();
        this.companyService = new TransportCompanyServiceImpl();
    }

    @Override
    public Vehicle getById(Integer id) {
        Vehicle v = repository.findById(id);
        if (v == null) {
            throw new EntityNotFoundException("Vehicle with ID " + id + " not found");
        }
        return v;
    }

    @Override
    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Vehicle vehicle) {

        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        }

        if (vehicle.getType() == null) {
            throw new IllegalArgumentException("Vehicle type must be provided");
        }

        // DTO → entity: company идва само с ID, fetch real entity
        if (vehicle.getCompany() == null || vehicle.getCompany().getId() == null) {
            throw new IllegalArgumentException("Vehicle must belong to a company");
        }

        TransportCompany company = companyService.getById(vehicle.getCompany().getId());
        vehicle.setCompany(company);

        if (vehicle.getCapacityTons() != null && vehicle.getCapacityTons() < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }

        repository.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {

        getById(vehicle.getId());

        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        }

        repository.merge(vehicle);
    }

    @Override
    public void delete(Integer id) {
        Vehicle v = getById(id);
        repository.delete(v);
    }
}
