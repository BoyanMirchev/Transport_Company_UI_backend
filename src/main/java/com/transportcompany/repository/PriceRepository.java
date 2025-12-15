package com.transportcompany.repository;

import com.transportcompany.entity.Price;

public class PriceRepository extends BaseRepository<Price> {

    public PriceRepository() {
        super(Price.class);
    }
}
