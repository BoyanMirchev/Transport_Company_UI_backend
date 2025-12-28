package com.transportcompany.mapper;

import com.transportcompany.dto.TicketDTO;
import com.transportcompany.entity.Ticket;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket ticket) {
        if (ticket == null) return null;

        return TicketDTO.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .price(
                        ticket.getPrice() != null
                                ? ticket.getPrice().getAmount()
                                : null
                )
                .build();
    }

    // Entity се сглобява в service слоя (Price се взима по ID там)
    public static Ticket toEntity(TicketDTO dto) {
        if (dto == null) return null;

        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setName(dto.getName());
        return ticket;
    }
}
