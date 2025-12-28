package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

    EmployeeDTO getById(Long id);

    List<EmployeeDTO> getAll();

    EmployeeDTO create(EmployeeDTO dto);

    EmployeeDTO update(Long id, EmployeeDTO dto);

    void delete(Long id);

    List<EmployeeDTO> findByPosition(String position);

    List<EmployeeDTO> findByCompanyId(Long companyId);
}