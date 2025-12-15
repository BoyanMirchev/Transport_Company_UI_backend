package com.transportcompany.service;

import com.transportcompany.dto.ClientDTO;
import java.util.List;

public interface ClientService {
    ClientDTO getById(Integer id);
    List<ClientDTO> getAll();
    ClientDTO create(ClientDTO dto);
    ClientDTO update(ClientDTO dto);
    void delete(Integer id);
    ClientDTO findByEmail(String email);
}
