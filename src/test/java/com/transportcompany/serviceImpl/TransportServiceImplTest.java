package com.transportcompany.serviceImpl;

import com.transportcompany.dto.TransportDTO;
import com.transportcompany.entity.Transport;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.repository.TransportRepository;
import com.transportcompany.serviceImpl.impl.TransportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransportServiceImplTest {

    private TransportServiceImpl service;
    private TransportRepository transportRepoMock;

    @BeforeEach
    void setUp() throws Exception {
        service = new TransportServiceImpl();
        transportRepoMock = Mockito.mock(TransportRepository.class);

        injectMock(service, "transportRepo", transportRepoMock);
    }

    @Test
    void getById_shouldThrowIllegalArgument_whenIdIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> service.getById(0L));
    }

    @Test
    void getById_shouldThrowEntityNotFound_whenTransportMissing() {
        when(transportRepoMock.findById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> service.getById(1L));
    }

    @Test
    void getById_shouldReturnDTO_whenTransportExists() {
        Transport transport = new Transport();
        transport.setId(1L);

        when(transportRepoMock.findById(1L)).thenReturn(transport);

        TransportDTO dto = service.getById(1L);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
    }

    @Test
    void delete_shouldCallRepositoryDelete_whenTransportExists() {
        Transport transport = new Transport();
        when(transportRepoMock.findById(1L)).thenReturn(transport);

        service.delete(1L);

        verify(transportRepoMock).delete(transport);
    }

    @Test
    void delete_shouldThrowEntityNotFound_whenMissing() {
        when(transportRepoMock.findById(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> service.delete(1L));
    }

    // ===== helper =====
    private void injectMock(Object target, String fieldName, Object mock)
            throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, mock);
    }
}
