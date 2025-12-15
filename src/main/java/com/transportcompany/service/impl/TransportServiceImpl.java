package com.transportcompany.service.impl;

import com.transportcompany.entity.Client;
import com.transportcompany.entity.Employee;
import com.transportcompany.entity.Transport;
import com.transportcompany.entity.Vehicle;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.TransportRepository;
import com.transportcompany.service.*;

import java.util.List;

public class TransportServiceImpl implements TransportService {

    private final TransportRepository repository = new TransportRepository();
    private final ClientService clientService = new ClientServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final VehicleService vehicleService = new VehicleServiceImpl();
    private final PriceService priceService = new PriceServiceImpl();

    @Override
    public Transport getById(Integer id) {
        Transport tr = repository.findById(id);
        if (tr == null) {
            throw new EntityNotFoundException("Transport with ID " + id + " not found");
        }
        return tr;
    }

    @Override
    public List<Transport> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Transport transport) {

        // === VALIDATION ===
        if (transport.getStartPoint() == null || transport.getStartPoint().isBlank()) {
            throw new IllegalArgumentException("Start point cannot be empty");
        }

        if (transport.getEndPoint() == null || transport.getEndPoint().isBlank()) {
            throw new IllegalArgumentException("End point cannot be empty");
        }

        if (transport.getDepartureDate() == null) {
            throw new IllegalArgumentException("Departure date is required");
        }

        if (transport.getPrice() == null) {
            throw new IllegalArgumentException("Transport must have a price");
        }

        if (transport.getPrice().getAmount() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }


        // === PRICE HANDLING (Critical Fix) ===
        if (transport.getPrice().getId() == 0) {
            // нов обект → първо го записваме
            priceService.create(transport.getPrice());
        } else {
            // вече съществува → зареждаме реалния
            transport.setPrice(priceService.getById(transport.getPrice().getId()));
        }


        // === FETCH REAL ENTITIES ===
        if (transport.getClient() != null) {
            Client existingClient = clientService.getById(transport.getClient().getId());
            transport.setClient(existingClient);
        }

        if (transport.getEmployee() != null) {
            Employee existingEmployee = employeeService.getById(transport.getEmployee().getId());
            transport.setEmployee(existingEmployee);
        }

        if (transport.getVehicle() != null) {
            Vehicle existingVehicle = vehicleService.getById(transport.getVehicle().getId());
            transport.setVehicle(existingVehicle);
        }

        if (transport.getCompany() != null) {
            // Ако имаш CompanyService, добавяме и това
            // transport.setCompany(companyService.getById(transport.getCompany().getId()));
        }

        // === SAVE TRANSPORT ===
        repository.save(transport);
    }

    @Override
    public void update(Transport transport) {
        getById(transport.getId()); // check exists
        repository.update(transport);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(getById(id));
    }

    @Override
    public List<Transport> getTransportsByClientId(Integer clientId) {
        return repository.findByClientId(clientId);
    }

    @Override
    public List<Transport> getTransportsByVehicleId(Integer vehicleId) {
        return repository.findByVehicleId(vehicleId);
    }

    @Override
    public List<Transport> getTransportsByEmployeeId(Integer employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}
