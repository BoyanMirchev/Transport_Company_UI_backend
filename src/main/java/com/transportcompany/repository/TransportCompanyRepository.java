package com.transportcompany.repository;

import com.transportcompany.entity.TransportCompany;

public class TransportCompanyRepository extends BaseRepository<TransportCompany> {

    public TransportCompanyRepository() {
        super(TransportCompany.class);
    }
}