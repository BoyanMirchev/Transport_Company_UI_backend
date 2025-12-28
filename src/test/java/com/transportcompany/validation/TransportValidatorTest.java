package com.transportcompany.validation;

import com.transportcompany.dto.TransportDTO;
import com.transportcompany.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransportValidatorTest {

    // ===================== POSITIVE =====================

    @Test
    void validateForCreate_shouldPass_whenDataIsValid() {
        TransportDTO dto = validTransportDTO();

        assertDoesNotThrow(() ->
                TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldPass_whenArrivalDateIsNull() {
        TransportDTO dto = validTransportDTO();
        dto.setArrivalDate(null);

        assertDoesNotThrow(() ->
                TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldPass_whenCargoWeightIsNull() {
        TransportDTO dto = validTransportDTO();
        dto.setCargoWeight(null);

        assertDoesNotThrow(() ->
                TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldPass_whenCargoWeightZero() {
        TransportDTO dto = validTransportDTO();
        dto.setCargoWeight(0.0);

        assertDoesNotThrow(() ->
                TransportValidator.validateForCreate(dto));
    }

    // ===================== DTO =====================

    @Test
    void validateForCreate_shouldThrow_whenDtoIsNull() {
        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(null));
    }

    //            START / END POINT

    @Test
    void validateForCreate_shouldThrow_whenStartPointNull() {
        TransportDTO dto = validTransportDTO();
        dto.setStartPoint(null);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenStartPointBlank() {
        TransportDTO dto = validTransportDTO();
        dto.setStartPoint("   ");

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenEndPointNull() {
        TransportDTO dto = validTransportDTO();
        dto.setEndPoint(null);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenEndPointBlank() {
        TransportDTO dto = validTransportDTO();
        dto.setEndPoint("");

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    // ===================== DATES =====================

    @Test
    void validateForCreate_shouldThrow_whenDepartureDateNull() {
        TransportDTO dto = validTransportDTO();
        dto.setDepartureDate(null);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenArrivalBeforeDeparture() {
        TransportDTO dto = validTransportDTO();
        dto.setArrivalDate(dto.getDepartureDate().minusDays(1));

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    // ===================== IDS =====================

    @Test
    void validateForCreate_shouldThrow_whenClientIdNull() {
        TransportDTO dto = validTransportDTO();
        dto.setClientId(null);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenEmployeeIdNull() {
        TransportDTO dto = validTransportDTO();
        dto.setEmployeeId(null);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenVehicleIdNull() {
        TransportDTO dto = validTransportDTO();
        dto.setVehicleId(null);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenCompanyIdNull() {
        TransportDTO dto = validTransportDTO();
        dto.setCompanyId(null);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_shouldThrow_whenPriceIdNull() {
        TransportDTO dto = validTransportDTO();
        dto.setPriceId(null);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    // ===================== CARGO WEIGHT =====================

    @Test
    void validateForCreate_shouldThrow_whenCargoWeightNegative() {
        TransportDTO dto = validTransportDTO();
        dto.setCargoWeight(-1.0);

        assertThrows(InvalidDataException.class,
                () -> TransportValidator.validateForCreate(dto));
    }

    // ===================== HELPER =====================

    private TransportDTO validTransportDTO() {
        TransportDTO dto = new TransportDTO();
        dto.setStartPoint("Sofia");
        dto.setEndPoint("Varna");
        dto.setDepartureDate(LocalDate.now());
        dto.setArrivalDate(LocalDate.now().plusDays(1));

        dto.setClientId(1L);
        dto.setEmployeeId(1L);
        dto.setVehicleId(1L);
        dto.setCompanyId(1L);
        dto.setPriceId(1L);

        dto.setCargoWeight(100.0);
        return dto;
    }
}
