package com.transportcompany;

import com.transportcompany.dto.ClientDTO;
import com.transportcompany.dto.TransportDTO;
import com.transportcompany.service.ClientService;
import com.transportcompany.service.TransportService;
import com.transportcompany.service.impl.ClientServiceImpl;
import com.transportcompany.service.impl.TransportServiceImpl;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        ClientService clientService = new ClientServiceImpl();
        TransportService transportService = new TransportServiceImpl();

        Integer companyId = 1; // вече съществуваща фирма в БД

        ClientDTO createdClient = clientService.create(
                ClientDTO.builder()
                        .name("Ivan Petrov")
                        .email("ivan@example.com")
                        .companyId(companyId)
                        .build()
        );

        System.out.println("Client created with ID = " + createdClient.getId());

        TransportDTO createdTransport = transportService.create(
                TransportDTO.builder()
                        .clientId(createdClient.getId())
                        .employeeId(1)
                        .vehicleId(1)
                        .companyId(companyId)
                        .priceId(1)
                        .startPoint("Sofia")
                        .endPoint("Varna")
                        .departureDate(LocalDate.now())
                        .build()
        );

        System.out.println("Transport created with ID = " + createdTransport.getId());
    }
}
