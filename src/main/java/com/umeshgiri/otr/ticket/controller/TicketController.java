package com.umeshgiri.otr.ticket.controller;

import com.umeshgiri.otr.auth.config.CurrentUser;
import com.umeshgiri.otr.auth.config.UserPrincipal;
import com.umeshgiri.otr.commonpayload.CommonResponse;
import com.umeshgiri.otr.commonpayload.CommonResponseStatus;
import com.umeshgiri.otr.ticket.model.Ticket;
import com.umeshgiri.otr.ticket.service.TicketApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    private TicketApi ticketApi;

    @PostMapping("/ticket")
    @PreAuthorize("hasAuthority('book_ticket')")
    public CommonResponse bookTicket(@CurrentUser UserPrincipal userPrincipal) {
        final Ticket ticket = ticketApi.bookTicket(userPrincipal.getId());

        return CommonResponse.builder()
                .status(CommonResponseStatus.SUCCESS)
                .message("Ticket Booked Successfully")
                .object(ticket.getId())
                .build();
    }

    @PostMapping("/ticket/payment/{ticketID}")
    @PreAuthorize("hasAuthority('make_payment')")
    public CommonResponse makePayment(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long ticketID) {
        ticketApi.makePayment(userPrincipal.getId(), ticketID);

        return CommonResponse.builder()
                .status(CommonResponseStatus.SUCCESS)
                .message("Payment Successful")
                .build();
    }

    @GetMapping("/tickets")
    @PreAuthorize("hasAuthority('read_self_ticket')")
    public CommonResponse listTickets(@CurrentUser UserPrincipal userPrincipal) {
        return CommonResponse.builder()
                .status(CommonResponseStatus.SUCCESS)
                .message("Fetched Successfully")
                .object(ticketApi.getTickets(userPrincipal.getId()))
                .build();
    }

    @GetMapping("/tickets/insights")
    @PreAuthorize("hasAuthority('ticket_insights')")
    public CommonResponse getInsights() {
        return CommonResponse.builder()
                .status(CommonResponseStatus.SUCCESS)
                .message("Fetched Successfully")
                .object(ticketApi.getInsights())
                .build();
    }
}
