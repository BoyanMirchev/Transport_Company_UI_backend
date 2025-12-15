package com.transportcompany.repository;

import com.transportcompany.entity.Transport;
import com.transportcompany.entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TransportRepository extends BaseRepository<Transport> {

    public TransportRepository() {
        super(Transport.class);
    }

    public List<Transport> findByClientId(Integer clientId) {
        try (Session session = getSession()) {
            Query<Transport> query = session.createQuery(
                    "FROM Transport t WHERE t.client.id = :clientId", Transport.class);
            query.setParameter("clientId", clientId);
            return query.list();
        }
    }

    public List<Transport> findByVehicleId(Integer vehicleId) {
        try (Session session = getSession()) {
            Query<Transport> query = session.createQuery(
                    "FROM Transport t WHERE t.vehicle.id = :vehicleId", Transport.class);
            query.setParameter("vehicleId", vehicleId);
            return query.list();
        }
    }

    public List<Transport> findByEmployeeId(Integer employeeId) {
        try (Session session = getSession()) {
            Query<Transport> query = session.createQuery(
                    "FROM Transport t WHERE t.employee.id = :employeeId", Transport.class);
            query.setParameter("employeeId", employeeId);
            return query.list();
        }
    }

    private Session getSession() {
        return com.transportcompany.config.HibernateUtil.getSessionFactory().openSession();
    }
}