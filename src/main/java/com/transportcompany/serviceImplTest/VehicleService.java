package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.VehicleDTO;
import java.util.List;

public interface VehicleService {

    VehicleDTO getById(Long id);

    VehicleDTO getByRegistrationNumber(String registrationNumber);

    List<VehicleDTO> getAll();

    VehicleDTO create(VehicleDTO dto);

    VehicleDTO update(Long id, VehicleDTO dto);

    void delete(Long id);
}
