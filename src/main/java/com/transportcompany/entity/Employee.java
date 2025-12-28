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
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String position;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @PrePersist
    @PreUpdate
    private void validate() {
        if (firstName == null || firstName.isBlank())
            throw new com.transportcompany.exceptions.InvalidDataException("First name required");
        if (lastName == null || lastName.isBlank())
            throw new com.transportcompany.exceptions.InvalidDataException("Last name required");
        if (salary == null)
            throw new com.transportcompany.exceptions.InvalidDataException("Salary required");
        if (salary.compareTo(java.math.BigDecimal.ZERO) < 0)
            throw new com.transportcompany.exceptions.InvalidDataException("Salary cannot be negative");
    }

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
