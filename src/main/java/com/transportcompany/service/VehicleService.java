package com.transportcompany.service;

import com.transportcompany.entity.Vehicle;
import java.util.List;

public interface VehicleService {

    Vehicle getById(Integer id);

    List<Vehicle> getAll();

    void create(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(Integer id);
}