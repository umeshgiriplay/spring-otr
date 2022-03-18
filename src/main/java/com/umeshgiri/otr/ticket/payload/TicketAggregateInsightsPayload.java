package com.umeshgiri.otr.ticket.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TicketAggregateInsightsPayload {
    private Long totalBookedTickets;
    private Long totalPaidTickets;
    private BigDecimal totalPaidAmount;

    private Long totalBookedTicketsToday;
    private Long totalPaidTicketsToday;
    private BigDecimal totalPaidAmountToday;

}
