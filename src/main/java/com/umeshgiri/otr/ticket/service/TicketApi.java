package com.umeshgiri.otr.ticket.service;

import com.umeshgiri.otr.auth.service.UserApi;
import com.umeshgiri.otr.commonexception.ResourceNotFoundException;
import com.umeshgiri.otr.ticket.model.Payment;
import com.umeshgiri.otr.ticket.model.Ticket;
import com.umeshgiri.otr.ticket.payload.TicketInsightsPayload;
import com.umeshgiri.otr.ticket.payload.TicketPayload;
import com.umeshgiri.otr.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TicketApi {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserApi userApi;

    public Ticket bookTicket(Long userId) {
        Ticket ticket = new Ticket();
        ticket.setUser(userApi.findEntityById(userId));
        return save(ticket);
    }

    public void makePayment(Long userId, Long ticketID) {
        Ticket ticket = findEntityByIdAndUserId(ticketID, userId);

        if (ticket.isPaymentDone()) {
            throw new IllegalArgumentException("Payment Already Completed For TicketID: " + ticketID);
        }

        Payment payment = new Payment();
        payment.setTicket(ticket);
        ticket.setPayment(payment);
        save(ticket);
    }

    public Ticket findEntityByIdAndUserId(Long ticketId, Long userId) {
        return ticketRepository.findTicketByIdAndUserId(ticketId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketId));
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Object getTickets(Long userId) {
        return ticketRepository.findTicketsByUserId(userId)
                .stream()
                .map(ticket -> new TicketPayload(ticket.getId(), ticket.getPrice(), ticket.isPaymentDone()))
                .collect(Collectors.toList());
    }

    public Object getInsights() {
        return new TicketInsightsPayload(ticketRepository.findDailyInsights(), ticketRepository.findInsights());
    }
}
