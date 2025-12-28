package com.transportcompany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "driver_qualification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private Long id;

    @Column(name = "qualification_name", nullable = false)
    private String name;

    // M:N with Employee
    @ManyToMany(mappedBy = "qualifications")
    private Set<Employee> employees;
}
//                ЛОГИКА НА ВРЪЗКИТЕ

//        @ManyToMany(mappedBy = "qualifications")
//        Защото това е обратната страна на връзката от Employee.
//
//           Името е "qualification_name" → точно като в MySQL таблицата.
//           Нямаме списъци — само employees.
