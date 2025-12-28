package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.TransportDTO;
import java.util.List;

public interface TransportService {

    TransportDTO getById(Long id);

    List<TransportDTO> getAll();

    TransportDTO create(TransportDTO dto);

    TransportDTO update(TransportDTO dto);


    void delete(Long id);

    List<TransportDTO> getPaidByCompany(Long companyId);
}
