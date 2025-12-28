package com.transportcompany.validation;

import com.transportcompany.dto.ClientDTO;
import com.transportcompany.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientValidatorTest {

    // ===================== POSITIVE =====================

    @Test
    void validateForCreate_shouldPass_whenDataIsValid() {
        ClientDTO dto = validClientDTO();

        assertDoesNotThrow(() ->
                ClientValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldPass_whenEmailIsNull() {
        ClientDTO dto = validClientDTO();
        dto.setEmail(null);

        assertDoesNotThrow(() ->
                ClientValidator.validateForCreate(dto));
    }

    // ===================== DTO =====================

    @Test
    void validateForCreate_shouldThrow_whenDtoIsNull() {
        assertThrows(InvalidDataException.class,
                () -> ClientValidator.validateForCreate(null));
    }

    // ===================== NAME =====================

    @Test
    void validateForCreate_shouldThrow_whenNameNull() {
        ClientDTO dto = validClientDTO();
        dto.setName(null);

        assertThrows(InvalidDataException.class,
                () -> ClientValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenNameBlank() {
        ClientDTO dto = validClientDTO();
        dto.setName("   ");

        assertThrows(InvalidDataException.class,
                () -> ClientValidator.validateForCreate(dto));
    }

    // ===================== COMPANY ID =====================

    @Test
    void validateForCreate_shouldThrow_whenCompanyIdNull() {
        ClientDTO dto = validClientDTO();
        dto.setCompanyId(null);

        assertThrows(InvalidDataException.class,
                () -> ClientValidator.validateForCreate(dto));
    }

    // ===================== EMAIL =====================

    @Test
    void validateForCreate_shouldThrow_whenEmailBlank() {
        ClientDTO dto = validClientDTO();
        dto.setEmail("   ");

        assertThrows(InvalidDataException.class,
                () -> ClientValidator.validateForCreate(dto));
    }

    // ===================== HELPER =====================

    private ClientDTO validClientDTO() {
        ClientDTO dto = new ClientDTO();
        dto.setName("Test Client");
        dto.setCompanyId(1L);
        dto.setEmail("client@test.com");
        return dto;
    }
}
