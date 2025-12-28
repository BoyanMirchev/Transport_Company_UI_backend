package com.transportcompany.serviceImplTest;

import java.util.List;

public interface BaseService<DTO, ID> {

    DTO getById(ID id);

    List<DTO> getAll();

    DTO create(DTO dto);

    DTO update(ID id, DTO dto);

    void delete(ID id);
}
