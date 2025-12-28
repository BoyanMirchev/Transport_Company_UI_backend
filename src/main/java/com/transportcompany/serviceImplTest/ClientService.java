package com.transportcompany.serviceImplTest;

import com.transportcompany.dto.ClientDTO;
import java.util.List;

public interface ClientService {

    ClientDTO getById(Long id);

    List<ClientDTO> getAll();

    ClientDTO create(ClientDTO dto);

    ClientDTO update(Long id, ClientDTO dto);

    void delete(Long id);

    ClientDTO findByEmail(String email);
}
