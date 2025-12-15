package com.transportcompany.service;

import com.transportcompany.entity.Transport;

import java.util.List;

public interface TransportService {

    Transport getById(Integer id);

    List<Transport> getAll();

    void create(Transport transport);

    void update(Transport transport);

    void delete(Integer id);

    // Допълнителни заявки
    List<Transport> getTransportsByClientId(Integer clientId);
    List<Transport> getTransportsByVehicleId(Integer vehicleId);
    List<Transport> getTransportsByEmployeeId(Integer employeeId);
}
