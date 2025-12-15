package com.transportcompany.service;

import java.util.List;

public interface BaseService<T, ID> {
    T getById(ID id);
    List<T> getAll();
    void create(T entity);
    void update(T entity);
    void delete(ID id);
}
         //CRUD - Create, Read, Update, Delete