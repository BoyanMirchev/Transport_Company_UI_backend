package com.transportcompany.service;

import com.transportcompany.entity.TransportCompany;

import java.util.List;

public interface TransportCompanyService {

    TransportCompany getById(Integer id);

    List<TransportCompany> getAll();

    void create(TransportCompany company);

    void update(TransportCompany company);

    void delete(Integer id);  //CRUD
}