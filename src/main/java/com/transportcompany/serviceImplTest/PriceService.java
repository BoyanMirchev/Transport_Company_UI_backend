package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.PriceDTO;
import java.util.List;

public interface PriceService {

    PriceDTO getById(Long id);

    List<PriceDTO> getAll();

    PriceDTO create(PriceDTO dto);

    PriceDTO update(Long id, PriceDTO dto);

    void delete(Long id);
}
