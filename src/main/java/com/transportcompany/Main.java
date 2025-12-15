package com.transportcompany;

import com.transportcompany.dto.*;
import com.transportcompany.entity.*;
import com.transportcompany.mapper.*;
import com.transportcompany.service.*;
import com.transportcompany.service.impl.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Transport Company Console UI (DTO Layer) ===");

        // Services
        TransportCompanyService companyService = new TransportCompanyServiceImpl();
        ClientService clientService = new ClientServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        PriceService priceService = new PriceServiceImpl();
        TransportService transportService = new TransportServiceImpl();

        // -------------------------
        // 1) Create Company (DTO)
        // -------------------------
        TransportCompanyDTO companyDTO = TransportCompanyDTO.builder()
                .name("TransCo")
                .address("Sofia")
                .phone("0888123456")
                .build();

        TransportCompany companyEntity = TransportCompanyMapper.toEntity(companyDTO);
        companyService.create(companyEntity);
        companyDTO = TransportCompanyMapper.toDTO(companyEntity);

        System.out.println("Company created: " + companyDTO);

        // -------------------------
        // 2) Create Client (DTO)
        // -------------------------
        ClientDTO clientDTO = ClientDTO.builder()
                .name("Ivan Petrov")
                .email("ivan@example.com")
                .companyId(companyDTO.getId())
                .debtor(false)
                .build();

        Client clientEntity = ClientMapper.toEntity(clientDTO);
        clientEntity.setCompany(companyService.getById(clientDTO.getCompanyId()));

        clientService.create(clientEntity);
        clientDTO = ClientMapper.toDTO(clientEntity);

        System.out.println("Client created: " + clientDTO);

        // -------------------------
        // 3) Create Employee (DTO)
        // -------------------------
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .firstName("Georgi")
                .lastName("Georgiev")
                .salary(1500.0)
                .companyId(companyDTO.getId())
                .build();

        Employee employeeEntity = EmployeeMapper.toEntity(employeeDTO);
        employeeEntity.setCompany(companyService.getById(employeeDTO.getCompanyId()));

        employeeService.create(employeeEntity);
        employeeDTO = EmployeeMapper.toDTO(employeeEntity);

        System.out.println("Employee created: " + employeeDTO);

        // -------------------------
        // 4) Create Vehicle (DTO)
        // -------------------------
        VehicleDTO vehicleDTO = VehicleDTO.builder()
                .registrationNumber("CB9889TT")
                .type("Truck")
                .capacityTons(5.0)
                .companyId(companyDTO.getId())
                .build();

        Vehicle vehicleEntity = VehicleMapper.toEntity(vehicleDTO);
        vehicleEntity.setCompany(companyService.getById(vehicleDTO.getCompanyId()));

        vehicleService.create(vehicleEntity);
        vehicleDTO = VehicleMapper.toDTO(vehicleEntity);

        System.out.println("Vehicle created: " + vehicleDTO);

        // -------------------------
        // 5) Create Price (DTO)
        // -------------------------
        PriceDTO priceDTO = PriceDTO.builder()
                .amount(120.00)
                .build();

        Price priceEntity = PriceMapper.toEntity(priceDTO);
        priceService.create(priceEntity);
        priceDTO = PriceMapper.toDTO(priceEntity);

        System.out.println("Price created: " + priceDTO);

        // -------------------------
        // 6) Create Transport (DTO)
        // -------------------------
        TransportDTO transportDTO = TransportDTO.builder()
                .clientId(clientDTO.getId())
                .employeeId(employeeDTO.getId())
                .vehicleId(vehicleDTO.getId())
                .companyId(companyDTO.getId())
                .startPoint("Sofia")
                .endPoint("Varna")
                .departureDate(LocalDate.now())
                .arrivalDate(LocalDate.now().plusDays(1))
                .priceId(priceDTO.getId())
                .build();

        Transport transportEntity = TransportMapper.toEntity(transportDTO);

        transportEntity.setClient(clientService.getById(transportDTO.getClientId()));
        transportEntity.setEmployee(employeeService.getById(transportDTO.getEmployeeId()));
        transportEntity.setVehicle(vehicleService.getById(transportDTO.getVehicleId()));
        transportEntity.setCompany(companyService.getById(transportDTO.getCompanyId()));
        transportEntity.setPrice(priceService.getById(transportDTO.getPriceId()));

        transportService.create(transportEntity);
        transportDTO = TransportMapper.toDTO(transportEntity);

        System.out.println("Transport created: " + transportDTO);

        System.out.println("\n=== END ===");
    }
}
     //        dto - Трябва да е в Service

     //        Трябват валидации на DTO и на entity

     //        Insert заявки , Update - заявки, Критериални заявки - Select (Това в тестовете)