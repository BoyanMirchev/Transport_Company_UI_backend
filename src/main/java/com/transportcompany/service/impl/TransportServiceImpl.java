package com.transportcompany.service.impl;

import com.transportcompany.dto.TransportDTO;
import com.transportcompany.entity.*;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.TransportMapper;
import com.transportcompany.repository.*;
import com.transportcompany.service.TransportService;
import com.transportcompany.validation.TransportValidator;

import java.time.LocalDate;
import java.util.List;

public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepo = new TransportRepository();
    private final ClientRepository clientRepo = new ClientRepository();
    private final EmployeeRepository employeeRepo = new EmployeeRepository();
    private final VehicleRepository vehicleRepo = new VehicleRepository();
    private final TransportCompanyRepository companyRepo = new TransportCompanyRepository();
    private final PriceRepository priceRepo = new PriceRepository();

    @Override
    public TransportDTO getById(Integer id) {
        Transport t = transportRepo.findById(id);
        if (t == null) throw new EntityNotFoundException("Transport with ID " + id + " not found");
        return TransportMapper.toDTO(t);
    }

    @Override
    public List<TransportDTO> getAll() {
        return transportRepo.findAll().stream().map(TransportMapper::toDTO).toList();
    }

    @Override
    public TransportDTO create(TransportDTO dto) {
        TransportValidator.validateForCreate(dto);

        Client c = clientRepo.findById(dto.getClientId());
        if (c == null) throw new EntityNotFoundException("Client not found: " + dto.getClientId());

        Employee e = employeeRepo.findById(dto.getEmployeeId());
        if (e == null) throw new EntityNotFoundException("Employee not found: " + dto.getEmployeeId());

        Vehicle v = vehicleRepo.findById(dto.getVehicleId());
        if (v == null) throw new EntityNotFoundException("Vehicle not found: " + dto.getVehicleId());

        TransportCompany comp = companyRepo.findById(dto.getCompanyId());
        if (comp == null) throw new EntityNotFoundException("Company not found: " + dto.getCompanyId());

        Price p = priceRepo.findById(dto.getPriceId());
        if (p == null) throw new EntityNotFoundException("Price not found: " + dto.getPriceId());

        Transport t = TransportMapper.toEntity(dto);
        t.setClient(c);
        t.setEmployee(e);
        t.setVehicle(v);
        t.setCompany(comp);
        t.setPrice(p);

        transportRepo.save(t);
        return TransportMapper.toDTO(t);
    }

    @Override
    public TransportDTO update(TransportDTO dto) {
        if (dto == null || dto.getId() == null) throw new com.transportcompany.exceptions.InvalidDataException("Transport id required for update");

        Transport existing = transportRepo.findById(dto.getId());
        if (existing == null) throw new EntityNotFoundException("Transport with ID " + dto.getId() + " not found");

        // update fields
        existing.setStartPoint(dto.getStartPoint());
        existing.setEndPoint(dto.getEndPoint());
        existing.setDepartureDate(dto.getDepartureDate());
        existing.setArrivalDate(dto.getArrivalDate());
        existing.setCargoDescription(dto.getCargoDescription());
        existing.setCargoWeight(dto.getCargoWeight());
        existing.setPaid(dto.isPaid());

        // update relations if provided
        if (dto.getClientId() != null) {
            Client c = clientRepo.findById(dto.getClientId());
            if (c == null) throw new EntityNotFoundException("Client not found: " + dto.getClientId());
            existing.setClient(c);
        }
        if (dto.getEmployeeId() != null) {
            Employee e = employeeRepo.findById(dto.getEmployeeId());
            if (e == null) throw new EntityNotFoundException("Employee not found: " + dto.getEmployeeId());
            existing.setEmployee(e);
        }
        if (dto.getVehicleId() != null) {
            Vehicle v = vehicleRepo.findById(dto.getVehicleId());
            if (v == null) throw new EntityNotFoundException("Vehicle not found: " + dto.getVehicleId());
            existing.setVehicle(v);
        }
        if (dto.getCompanyId() != null) {
            TransportCompany comp = companyRepo.findById(dto.getCompanyId());
            if (comp == null) throw new EntityNotFoundException("Company not found: " + dto.getCompanyId());
            existing.setCompany(comp);
        }
        if (dto.getPriceId() != null) {
            Price p = priceRepo.findById(dto.getPriceId());
            if (p == null) throw new EntityNotFoundException("Price not found: " + dto.getPriceId());
            existing.setPrice(p);
        }

        transportRepo.merge(existing);
        return TransportMapper.toDTO(existing);
    }

    @Override
    public void delete(Integer id) {
        Transport t = transportRepo.findById(id);
        if (t == null) throw new EntityNotFoundException("Transport with ID " + id + " not found");
        transportRepo.delete(t);
    }

    @Override
    public List<TransportDTO> getByClientId(Integer clientId) {
        return transportRepo.findByClientId(clientId).stream().map(TransportMapper::toDTO).toList();
    }

    @Override
    public List<TransportDTO> getPaidByCompany(Integer companyId) {
        return transportRepo.findPaidByCompany(companyId).stream().map(TransportMapper::toDTO).toList();
    }

    @Override
    public List<TransportDTO> getByDepartureBetween(LocalDate from, LocalDate to) {
        return transportRepo.findByDepartureBetween(from, to).stream().map(TransportMapper::toDTO).toList();
    }
}
