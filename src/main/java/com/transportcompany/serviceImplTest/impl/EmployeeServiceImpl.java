package com.transportcompany.serviceImplTest.impl;

import com.transportcompany.dto.EmployeeDTO;
import com.transportcompany.entity.Employee;
import com.transportcompany.entity.TransportCompany;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.mapper.EmployeeMapper;
import com.transportcompany.repository.EmployeeRepository;
import com.transportcompany.repository.TransportCompanyRepository;
import com.transportcompany.serviceImplTest.EmployeeService;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository = new EmployeeRepository();
    private final TransportCompanyRepository companyRepository = new TransportCompanyRepository();

   // Валидация
    @Override
    public EmployeeDTO getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID");
        }

        Employee employee = repository.findById(id);
        if (employee == null) {
            throw new EntityNotFoundException(
                    "Employee with ID " + id + " not found"
            );
        }
        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {
        validate(dto);

        TransportCompany company = companyRepository.findById(dto.getCompanyId());
        if (company == null) {
            throw new EntityNotFoundException(
                    "Company with ID " + dto.getCompanyId() + " not found"
            );
        }

        Employee employee = EmployeeMapper.toEntity(dto);
        employee.setCompany(company);

        repository.save(employee);
        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee existing = repository.findById(id);
        if (existing == null) {
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }

        validate(dto);

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setPosition(dto.getPosition());
        existing.setSalary(dto.getSalary());

        if (dto.getCompanyId() != null) {
            TransportCompany company = companyRepository.findById(dto.getCompanyId());
            if (company == null) {
                throw new EntityNotFoundException(
                        "Company with ID " + dto.getCompanyId() + " not found"
                );
            }
            existing.setCompany(company);
        }

        repository.merge(existing);
        return EmployeeMapper.toDTO(existing);
    }

    @Override
    public void delete(Long id) {
        Employee employee = repository.findById(id);
        if (employee == null) {
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }
        repository.delete(employee);
    }

    @Override
    public List<EmployeeDTO> findByPosition(String position) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getPosition() != null &&
                        e.getPosition().equalsIgnoreCase(position))
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    @Override
    public List<EmployeeDTO> findByCompanyId(Long companyId) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getCompany() != null &&
                        e.getCompany().getId().equals(companyId))
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    private void validate(EmployeeDTO dto) {
        if (dto.getFirstName() == null || dto.getFirstName().isBlank())
            throw new IllegalArgumentException("Employee first name cannot be empty");

        if (dto.getLastName() == null || dto.getLastName().isBlank())
            throw new IllegalArgumentException("Employee last name cannot be empty");

        if (dto.getSalary() != null && dto.getSalary().compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Salary cannot be negative");
    }
}