package com.transportcompany.repository;

import com.transportcompany.entity.Employee;
import java.util.List;

public class EmployeeRepository extends BaseRepository<Employee> {
    public EmployeeRepository() { super(Employee.class); }

    public List<Employee> findByPosition(String position) {
        return inSession(s -> s.createQuery(
                        "select e from Employee e where lower(e.position) = lower(:p)", Employee.class)
                .setParameter("p", position)
                .getResultList());
    }

    public List<Employee> findByCompanyId(Integer companyId) {
        return inSession(s -> s.createQuery(
                        "select e from Employee e where e.company.id = :cid", Employee.class)
                .setParameter("cid", companyId)
                .getResultList());
    }
}
// Тестове, където има заявки