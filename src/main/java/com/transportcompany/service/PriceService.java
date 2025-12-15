package com.transportcompany.service;

import com.transportcompany.entity.Price;
import java.util.List;

public interface PriceService {

    Price getById(Integer id);

    List<Price> getAll();

    void create(Price price);

    void update(Price price);

    void delete(Integer id);
}