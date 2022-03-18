package com.umeshgiri.otr.ticket.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TicketInsightsPayload {
    private List<TicketDailyInsightsPayload> dailyInsights;
    private TicketAggregateInsightsPayload aggregateInsights;
}
