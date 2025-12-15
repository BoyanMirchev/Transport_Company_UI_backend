package com.transportcompany.config;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;   // Transaction Helper

public final class HibernateTx {

    private HibernateTx() {}

    public static <T> T inTx(Function<Session, T> work) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            T result = work.apply(session);
            tx.commit();
            return result;
        } catch (RuntimeException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    public static void inTxVoid(java.util.function.Consumer<Session> work) {
        inTx(session -> { work.accept(session); return null; });
    }
}