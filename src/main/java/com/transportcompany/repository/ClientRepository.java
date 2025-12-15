package com.transportcompany.repository;

import com.transportcompany.entity.Client;

public class ClientRepository extends BaseRepository<Client> {

    public ClientRepository() {
        super(Client.class);
    }
}