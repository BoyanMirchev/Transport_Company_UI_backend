package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.TicketDTO;
import java.util.List;

public interface TicketService {

    TicketDTO getById(Long id);

    List<TicketDTO> getAll();

    TicketDTO create(TicketDTO dto);

    TicketDTO update(Long id, TicketDTO dto);

    void delete(Long id);
}
