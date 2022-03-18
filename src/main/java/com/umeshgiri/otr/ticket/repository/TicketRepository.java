package com.umeshgiri.otr.ticket.repository;

import com.umeshgiri.otr.ticket.model.Ticket;
import com.umeshgiri.otr.ticket.payload.TicketAggregateInsightsPayload;
import com.umeshgiri.otr.ticket.payload.TicketDailyInsightsPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findTicketByIdAndUserId(Long ticketId, Long userId);

    List<Ticket> findTicketsByUserId(Long userId);

    @Query(name = "find_insights", nativeQuery = true)
    TicketAggregateInsightsPayload findInsights();

    @Query(name = "find_daily_insights", nativeQuery = true)
    List<TicketDailyInsightsPayload> findDailyInsights();
}
