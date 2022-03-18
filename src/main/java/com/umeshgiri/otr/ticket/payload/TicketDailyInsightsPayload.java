package com.umeshgiri.otr.ticket.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TicketDailyInsightsPayload {
    private String date;
    private Long totalBookedTickets;
    private Long totalPaidTickets;
    private Long totalUnPaidTickets;
    private BigDecimal totalPaidAmount;
}
