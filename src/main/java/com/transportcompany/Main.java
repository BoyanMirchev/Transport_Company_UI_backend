package com.transportcompany;

import com.transportcompany.dto.*;
import com.transportcompany.entity.VehicleType;
import com.transportcompany.exceptions.EntityNotFoundException;
import com.transportcompany.serviceImplTest.*;
import com.transportcompany.serviceImplTest.impl.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        TransportCompanyService companyService = new TransportCompanyServiceImpl();
        PriceService priceService = new PriceServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        ClientService clientService = new ClientServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        TransportService transportService = new TransportServiceImpl();
        TicketService service = new TicketServiceImpl();

        // 1. Company
        TransportCompanyDTO company = companyService.create(
                TransportCompanyDTO.builder()
                        .name("TransLogistix EOOD")
                        .address("Sofia")
                        .phone("0888123456")
                        .revenue(BigDecimal.ZERO)
                        .build()
        );

        // 2. Price
        PriceDTO price = priceService.create(
                PriceDTO.builder()
                        .amount(new BigDecimal("120.00"))
                        .build()
        );



        // 3. Employee
        EmployeeDTO employee = employeeService.create(
                EmployeeDTO.builder()
                        .firstName("Georgi")
                        .lastName("Ivanov")
                        .salary(new BigDecimal("2000"))
                        .companyId(company.getId())
                        .build()
        );

        // 4. Client
        ClientDTO client = clientService.create(
                ClientDTO.builder()
                        .name("Ivan Petrov")
                        .email("ivan@example.com")
                        .companyId(company.getId())
                        .build()
        );

        // 5. Vehicle
        VehicleDTO vehicle = vehicleService.create(
                VehicleDTO.builder()
                        .registrationNumber("CB1262ВЕ")
                        .type(VehicleType.Truck)
                        .capacityTons(5.0)
                        .companyId(company.getId())
                        .build()
        );

        // 6. Transport
        TransportDTO transport = transportService.create(
                TransportDTO.builder()
                        .clientId(client.getId())
                        .employeeId(employee.getId())
                        .vehicleId(vehicle.getId())
                        .companyId(company.getId())
                        .priceId(price.getId())
                        .startPoint("Burgas")
                        .endPoint("Varna")
                        .departureDate(LocalDate.now())
                        .build()
        );



       try {
            TicketDTO ticket = service.getById(1L);
            System.out.println(ticket);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            EmployeeDTO fetchedEmployee = employeeService.getById(employee.getId());
            System.out.println(fetchedEmployee);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            ClientDTO clientResult = clientService.getById(client.getId());
            System.out.println(clientResult);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            PriceDTO priceResult = priceService.getById(price.getId());
            System.out.println(priceResult);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            TransportCompanyDTO companyResult = companyService.getById(company.getId());
            System.out.println(companyResult);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            VehicleDTO vehicleResult = vehicleService.getById(vehicle.getId());
            System.out.println(vehicleResult);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            TransportDTO transportResult = transportService.getById(transport.getId());
            System.out.println(transportResult);
        } catch (EntityNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }



        System.out.println("Transport created with ID = " + transport.getId());
    }



     // Валидацията е в serviceImpl слоя, защото там е бизнес логиката.
    //  Main само обработва резултата и показва съобщения.
}
