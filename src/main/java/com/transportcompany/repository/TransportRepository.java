package com.transportcompany.repository;

import com.transportcompany.entity.Transport;
import java.time.LocalDate;
import java.util.List;

public class TransportRepository extends BaseRepository<Transport> {
    public TransportRepository() { super(Transport.class); }

    public List<Transport> findByClientId(Long clientId) {
        return inSession(s -> s.createQuery(
                        "select t from Transport t where t.client.id = :id", Transport.class)
                .setParameter("id", clientId)
                .getResultList());
    }

    public List<Transport> findPaidByCompany(Long companyId) {
        return inSession(s -> s.createQuery(
                        "select t from Transport t where t.company.id = :cid and t.paid = true", Transport.class)
                .setParameter("cid", companyId)
                .getResultList());
    }

    public List<Transport> findByDepartureBetween(LocalDate from, LocalDate to) {
        return inSession(s -> s.createQuery(
                        "select t from Transport t where t.departureDate between :from and :to", Transport.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList());
    }
}
