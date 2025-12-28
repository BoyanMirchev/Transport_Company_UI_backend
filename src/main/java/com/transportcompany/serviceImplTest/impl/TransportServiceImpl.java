package com.transportcompany.serviceImplTest.impl;

import com.transportcompany.dto.TransportDTO;
import com.transportcompany.entity.*;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.TransportMapper;
import com.transportcompany.repository.*;
import com.transportcompany.serviceImplTest.TransportService;

import java.util.List;

public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepo = new TransportRepository();
    private final TransportCompanyRepository companyRepo = new TransportCompanyRepository();
    private final ClientRepository clientRepo = new ClientRepository();
    private final EmployeeRepository employeeRepo = new EmployeeRepository();
    private final VehicleRepository vehicleRepo = new VehicleRepository();
    private final PriceRepository priceRepo = new PriceRepository();

    //Валидация
    @Override
    public TransportDTO getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid transport ID");
        }

        Transport transport = transportRepo.findById(id);
        if (transport == null) {
            throw new EntityNotFoundException(
                    "Transport with ID " + id + " not found"
            );
        }
        return TransportMapper.toDTO(transport);
    }

    @Override
    public List<TransportDTO> getAll() {
        return transportRepo.findAll()
                .stream()
                .map(TransportMapper::toDTO)
                .toList();
    }

    @Override
    public TransportDTO create(TransportDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Transport data is required");
        }

        Transport transport = buildEntity(dto);
        transportRepo.save(transport);
        return TransportMapper.toDTO(transport);
    }

    @Override
    public TransportDTO update(TransportDTO dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Transport ID is required");
        }

        Transport existing = transportRepo.findById(dto.getId());
        if (existing == null) {
            throw new EntityNotFoundException("Transport with ID " + dto.getId() + " not found");
        }

        existing.setStartPoint(dto.getStartPoint());
        existing.setEndPoint(dto.getEndPoint());
        existing.setDepartureDate(dto.getDepartureDate());
        existing.setArrivalDate(dto.getArrivalDate());
        existing.setPaid(dto.isPaid());

        transportRepo.merge(existing);
        return TransportMapper.toDTO(existing);
    }

    @Override
    public void delete(Long id) {
        Transport transport = transportRepo.findById(id);
        if (transport == null) {
            throw new EntityNotFoundException("Transport with ID " + id + " not found");
        }
        transportRepo.delete(transport);
    }

    @Override
    public List<TransportDTO> getPaidByCompany(Long companyId) {
        return transportRepo.findPaidByCompany(companyId)
                .stream()
                .map(TransportMapper::toDTO)
                .toList();
    }

    // ================= PRIVATE =================

    private Transport buildEntity(TransportDTO dto) {

        TransportCompany company = companyRepo.findById(dto.getCompanyId());
        if (company == null) {
            throw new EntityNotFoundException("Company not found");
        }

        Client client = clientRepo.findById(dto.getClientId());
        if (client == null) {
            throw new EntityNotFoundException("Client not found");
        }

        Employee employee = employeeRepo.findById(dto.getEmployeeId());
        if (employee == null) {
            throw new EntityNotFoundException("Employee not found");
        }

        Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId());
        if (vehicle == null) {
            throw new EntityNotFoundException("Vehicle not found");
        }

        Price price = priceRepo.findById(dto.getPriceId());
        if (price == null) {
            throw new EntityNotFoundException("Price not found");
        }

        Transport transport = new Transport();
        transport.setCompany(company);
        transport.setClient(client);
        transport.setEmployee(employee);
        transport.setVehicle(vehicle);
        transport.setPrice(price);

        transport.setStartPoint(dto.getStartPoint());
        transport.setEndPoint(dto.getEndPoint());
        transport.setDepartureDate(dto.getDepartureDate());
        transport.setArrivalDate(dto.getArrivalDate());
        transport.setPaid(dto.isPaid());

        return transport;
    }
}
