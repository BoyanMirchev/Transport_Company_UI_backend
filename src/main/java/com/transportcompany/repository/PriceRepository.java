package com.transportcompany.repository;

import com.transportcompany.entity.Price;

import java.math.BigDecimal;

public class PriceRepository extends BaseRepository<Price> {

    public PriceRepository() {
        super(Price.class);
    }

    public Price findByAmount(BigDecimal amount) {
        return inSession(session ->
                session.createQuery(
                                "select p from Price p where p.amount = :amount",
                                Price.class
                        )
                        .setParameter("amount", amount)
                        .uniqueResult()
        );
    }
}