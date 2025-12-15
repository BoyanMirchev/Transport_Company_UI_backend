package com.transportcompany.repository;

import com.transportcompany.entity.Client;

public class ClientRepository extends BaseRepository<Client> {
    public ClientRepository() { super(Client.class); }

    public Client findByEmail(String email) {
        return inSession(s -> s.createQuery(
                        "select c from Client c where lower(c.email) = lower(:email)", Client.class)
                .setParameter("email", email)
                .uniqueResult());
    }
}
