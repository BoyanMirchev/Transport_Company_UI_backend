package com.transportcompany.service;

import com.transportcompany.entity.Client;

public interface ClientService extends BaseService<Client, Integer> {
    Client findByEmail(String email);
}
