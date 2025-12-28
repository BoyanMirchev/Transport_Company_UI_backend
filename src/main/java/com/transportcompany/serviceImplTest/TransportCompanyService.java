package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.TransportCompanyDTO;
import java.util.List;

public interface TransportCompanyService {

    TransportCompanyDTO getById(Long id);

    List<TransportCompanyDTO> getAll();

    TransportCompanyDTO create(TransportCompanyDTO dto);

    TransportCompanyDTO update(TransportCompanyDTO dto);

    void delete(Long id);
}
