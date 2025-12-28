package com.transportcompany.serviceImplTest.impl;

import com.transportcompany.dto.VehicleDTO;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.entity.Vehicle;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.VehicleMapper;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.repository.VehicleRepository;
import com.transportcompany.serviceImplTest.VehicleService;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepo = new VehicleRepository();
    private final TransportCompanyRepository companyRepo = new TransportCompanyRepository();

           // Валидация
    @Override
    public VehicleDTO getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid vehicle ID");
        }

        Vehicle v = vehicleRepo.findById(id);
        if (v == null) {
            throw new EntityNotFoundException("Vehicle not found");
        }
        return VehicleMapper.toDTO(v);
    }

    @Override
    public List<VehicleDTO> getAll() {
        return vehicleRepo.findAll()
                .stream()
                .map(VehicleMapper::toDTO)
                .toList();
    }

    @Override
    public VehicleDTO create(VehicleDTO dto) {
        Vehicle vehicle = VehicleMapper.toEntity(dto);

        TransportCompany company = companyRepo.findById(dto.getCompanyId());
        if (company == null)
            throw new EntityNotFoundException("Company not found");

        vehicle.setCompany(company);
        vehicleRepo.save(vehicle);
        return VehicleMapper.toDTO(vehicle);
    }

    @Override
    public VehicleDTO update(Long id, VehicleDTO dto) {
        Vehicle existing = vehicleRepo.findById(id);
        if (existing == null)
            throw new EntityNotFoundException("Vehicle not found");

        existing.setRegistrationNumber(dto.getRegistrationNumber());
        existing.setCapacityTons(dto.getCapacityTons());

        vehicleRepo.merge(existing);
        return VehicleMapper.toDTO(existing);
    }

    @Override
    public void delete(Long id) {
        Vehicle v = vehicleRepo.findById(id);
        if (v == null)
            throw new EntityNotFoundException("Vehicle not found");
        vehicleRepo.delete(v);
    }
    @Override
    public VehicleDTO getByRegistrationNumber(String registrationNumber) {
        Vehicle v = vehicleRepo.findByRegistrationNumber(registrationNumber);
        if (v == null) {
            throw new EntityNotFoundException(
                    "Vehicle with registration number " + registrationNumber + " not found"
            );
        }
        return VehicleMapper.toDTO(v);
    }
}