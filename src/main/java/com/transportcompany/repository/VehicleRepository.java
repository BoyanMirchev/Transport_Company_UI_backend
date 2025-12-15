package com.transportcompany.repository;

import com.transportcompany.entity.Vehicle;

public class VehicleRepository extends BaseRepository<Vehicle> {

    public VehicleRepository() {
        super(Vehicle.class);
    }
}