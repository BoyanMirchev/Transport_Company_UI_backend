package com.transportcompany.service.impl;

import com.transportcompany.entity.Employee;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.EmployeeRepository;
import com.transportcompany.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository = new EmployeeRepository();

    @Override
    public Employee getById(Integer id) {
        Employee emp = repository.findById(id);
        if (emp == null)
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        return emp;
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Employee employee) {

        // === ВАЛИДАЦИИ ===

        if (employee.getFirstName() == null || employee.getFirstName().isBlank())
            throw new IllegalArgumentException("Employee first name cannot be empty");

        if (employee.getLastName() == null || employee.getLastName().isBlank())
            throw new IllegalArgumentException("Employee last name cannot be empty");

        if (employee.getSalary() != null && employee.getSalary() < 0)
            throw new IllegalArgumentException("Salary cannot be negative");

        repository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        getById(employee.getId());

        if (employee.getFirstName() == null || employee.getFirstName().isBlank())
            throw new IllegalArgumentException("Employee first name cannot be empty");

        if (employee.getLastName() == null || employee.getLastName().isBlank())
            throw new IllegalArgumentException("Employee last name cannot be empty");

        if (employee.getSalary() != null && employee.getSalary() < 0)
            throw new IllegalArgumentException("Salary cannot be negative");

        repository.update(employee);
    }

    @Override
    public void delete(Integer id) {
        Employee e = getById(id);
        repository.delete(e);
    }

    @Override
    public List<Employee> findByPosition(String position) {
        return repository.findAll().stream()
                .filter(e -> e.getPosition() != null && e.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findByCompanyId(Integer companyId) {
        return repository.findAll().stream()
                .filter(e -> e.getCompany() != null && e.getCompany().getId() == companyId)
                .collect(Collectors.toList());
    }
}
