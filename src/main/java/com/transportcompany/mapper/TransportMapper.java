package com.transportcompany.mapper;

import com.transportcompany.dto.TransportDTO;
import com.transportcompany.entity.Transport;

public class TransportMapper {

    public static TransportDTO toDTO(Transport t) {
        if (t == null) return null;

        return TransportDTO.builder()
                .id(t.getId())
                .startPoint(t.getStartPoint())
                .endPoint(t.getEndPoint())
                .departureDate(t.getDepartureDate())
                .arrivalDate(t.getArrivalDate())
                .cargoDescription(t.getCargoDescription())
                .cargoWeight(t.getCargoWeight())
                .paid(t.isPaid())
                .clientId(t.getClient() != null ? t.getClient().getId() : null)
                .employeeId(t.getEmployee() != null ? t.getEmployee().getId() : null)
                .vehicleId(t.getVehicle() != null ? t.getVehicle().getId() : null)
                .companyId(t.getCompany() != null ? t.getCompany().getId() : null)
                .priceId(t.getPrice() != null ? t.getPrice().getId() : null)
                .build();
    }

    //
   //   Entity се доизгражда в service слоя
   //   - Client
    //  - Employee
    //  - Vehicle
    //  - Company
    //  - Price
    //
    public static Transport toEntity(TransportDTO dto) {
        if (dto == null) return null;

        Transport t = new Transport();
        t.setId(dto.getId()); // null при create, OK
        t.setStartPoint(dto.getStartPoint());
        t.setEndPoint(dto.getEndPoint());
        t.setDepartureDate(dto.getDepartureDate());
        t.setArrivalDate(dto.getArrivalDate());
        t.setCargoDescription(dto.getCargoDescription());
        t.setCargoWeight(dto.getCargoWeight());
        t.setPaid(dto.isPaid());
        return t;
    }
}
