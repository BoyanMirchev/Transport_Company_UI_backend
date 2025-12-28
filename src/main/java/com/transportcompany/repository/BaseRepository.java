package com.transportcompany.repository;

import com.transportcompany.config.HibernateTx;

import java.util.List;

public abstract class BaseRepository<T> {

    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(Long id) {
        return HibernateTx.inTx(s -> s.get(entityClass, id));
    }

    public List<T> findAll() {
        return HibernateTx.inTx(s ->
                s.createQuery("from " + entityClass.getSimpleName(), entityClass)
                        .getResultList()
        );
    }

    public void save(T entity) {
        HibernateTx.inTxVoid(s -> s.persist(entity));
    }

    public T merge(T entity) {
        return HibernateTx.inTx(s -> s.merge(entity));
    }

    public void delete(T entity) {
        HibernateTx.inTxVoid(s -> s.remove(entity));
    }
    protected <R> R inSession(java.util.function.Function<org.hibernate.Session, R> work) {
        return HibernateTx.inTx(work);
    }
}


