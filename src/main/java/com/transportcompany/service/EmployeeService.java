package com.transportcompany.service;

import com.transportcompany.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getById(Integer id);

    List<Employee> getAll();

    void create(Employee employee);

    void update(Employee employee);

    void delete(Integer id);

    List<Employee> findByPosition(String position);

    List<Employee> findByCompanyId(Integer companyId);
}