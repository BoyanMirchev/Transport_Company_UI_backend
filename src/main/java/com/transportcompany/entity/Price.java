package com.transportcompany.entity;

import com.transportcompany.exceptions.InvalidDataException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @OneToMany(mappedBy = "price")
    private Set<Transport> transports;

    @OneToMany(mappedBy = "price")
    private Set<Ticket> tickets;

    @PrePersist
    @PreUpdate
    private void validate() {
        if (amount == null) {
            throw new InvalidDataException("Price amount is required");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Price cannot be negative");
        }
    }
}
