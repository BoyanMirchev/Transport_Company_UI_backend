package com.transportcompany.validation;

import com.transportcompany.dto.ClientDTO;

public final class ClientValidator {
    private ClientValidator(){}
    public static void validateForCreate(ClientDTO dto) {
        Validation.notNull(dto, "ClientDTO is null");
        Validation.notBlank(dto.getName(), "Client name is required");
        Validation.notNull(dto.getCompanyId(), "Client companyId is required");
        if (dto.getEmail() != null) Validation.require(!dto.getEmail().isBlank(), "Email cannot be blank");
    }
}
