package com.transportcompany.repository;

import com.transportcompany.entity.Employee;

public class EmployeeRepository extends BaseRepository<Employee> {

    public EmployeeRepository() {
        super(Employee.class);
    }
}
