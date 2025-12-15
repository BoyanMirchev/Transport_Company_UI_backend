package com.transportcompany.service;

import com.transportcompany.dto.TransportDTO;
import java.time.LocalDate;
import java.util.List;

public interface TransportService {
    TransportDTO getById(Integer id);
    List<TransportDTO> getAll();
    TransportDTO create(TransportDTO dto);
    TransportDTO update(TransportDTO dto);
    void delete(Integer id);

    List<TransportDTO> getByClientId(Integer clientId);
    List<TransportDTO> getPaidByCompany(Integer companyId);
    List<TransportDTO> getByDepartureBetween(LocalDate from, LocalDate to);
}