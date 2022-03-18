package com.umeshgiri.otr.ticket.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TicketPayload {
    private Long id;
    private BigDecimal price;
    private boolean paymentDone;
}
