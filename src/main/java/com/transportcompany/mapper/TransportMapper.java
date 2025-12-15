package com.transportcompany.mapper;

import com.transportcompany.dto.TransportDTO;
import com.transportcompany.entity.Transport;

public class TransportMapper {

    public static TransportDTO toDTO(Transport transport) {
        if (transport == null) return null;

        return TransportDTO.builder()
                .id(transport.getId())
                .startPoint(transport.getStartPoint())
                .endPoint(transport.getEndPoint())
                .departureDate(transport.getDepartureDate())
                .arrivalDate(transport.getArrivalDate())
                .clientId(transport.getClient() != null ? transport.getClient().getId() : null)
                .employeeId(transport.getEmployee() != null ? transport.getEmployee().getId() : null)
                .vehicleId(transport.getVehicle() != null ? transport.getVehicle().getId() : null)
                .priceId(transport.getPrice() != null ? transport.getPrice().getId() : null)
                .companyId(transport.getCompany() != null ? transport.getCompany().getId() : null)
                .build();
    }

    public static Transport toEntity(TransportDTO dto) {
        if (dto == null) return null;

        Transport t = new Transport();
        t.setId(dto.getId());
        t.setStartPoint(dto.getStartPoint());
        t.setEndPoint(dto.getEndPoint());
        t.setDepartureDate(dto.getDepartureDate());
        t.setArrivalDate(dto.getArrivalDate());
        return t;
    }
}