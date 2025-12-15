package com.transportcompany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String position;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    // Many employees belong to one company
    @ManyToOne
    @JoinColumn(name = "company_id")
    private TransportCompany company;

    // M:N relationship with driver_qualification
    @ManyToMany
    @JoinTable(
            name = "employee_qualification",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private Set<DriverQualification> qualifications;

    // One employee → Many transports
    @OneToMany(mappedBy = "employee")
    private Set<Transport> transports;
}

//                                 ЛОГИКА ЗА ВРЪЗКИТЕ

//                     Employee → Company → Many employees per company
//
//                           Нормална логика.
//
//                        Employee → Qualifications (M:N)
//
//                                  Защото:
//
//                       един шофьор може да има много квалификации
//
//                       една квалификация може да се притежава от много шофьори
//
//                       Точно затова използваме join table employee_qualification.
//
//                                ✔ Employee → Transports (1:M)
//
//                           Един шофьор може да изпълни много превози.
