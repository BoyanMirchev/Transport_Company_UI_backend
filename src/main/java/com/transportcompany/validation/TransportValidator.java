package com.transportcompany.validation;

import com.transportcompany.dto.TransportDTO;

public final class TransportValidator {
    private TransportValidator(){}
    public static void validateForCreate(TransportDTO dto) {
        Validation.notNull(dto, "TransportDTO is null");
        Validation.notBlank(dto.getStartPoint(), "Start point required");
        Validation.notBlank(dto.getEndPoint(), "End point required");
        Validation.notNull(dto.getDepartureDate(), "Departure date required");
        Validation.notNull(dto.getClientId(), "clientId required");
        Validation.notNull(dto.getEmployeeId(), "employeeId required");
        Validation.notNull(dto.getVehicleId(), "vehicleId required");
        Validation.notNull(dto.getCompanyId(), "companyId required");
        Validation.notNull(dto.getPriceId(), "priceId required");
        if (dto.getArrivalDate() != null) {
            Validation.require(!dto.getArrivalDate().isBefore(dto.getDepartureDate()),
                    "Arrival date cannot be before departure date");
        }
        if (dto.getCargoWeight() != null) Validation.require(dto.getCargoWeight() >= 0, "Cargo weight cannot be negative");
    }
}
