package com.transportcompany.config;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;   // Transaction Helper

public final class HibernateTx {

    private HibernateTx() {}

    public static <T> T inTx(Function<Session, T> work) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            T result = work.apply(session);
            tx.commit();
            return result;
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    public static void inTxVoid(java.util.function.Consumer<Session> work) {
        inTx(session -> {
            work.accept(session);
            return null;
        });
    }
}