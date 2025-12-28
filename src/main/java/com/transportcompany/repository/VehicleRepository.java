package com.transportcompany.repository;

import com.transportcompany.config.HibernateUtil;
import com.transportcompany.entity.Vehicle;
import org.hibernate.Session;

public class VehicleRepository extends BaseRepository<Vehicle> {

    public VehicleRepository() {
        super(Vehicle.class);
    }

    public Vehicle findByRegistrationNumber(String regNumber) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                            "FROM Vehicle v WHERE v.registrationNumber = :reg",
                            Vehicle.class
                    )
                    .setParameter("reg", regNumber)
                    .uniqueResult();
        }
    }
}
