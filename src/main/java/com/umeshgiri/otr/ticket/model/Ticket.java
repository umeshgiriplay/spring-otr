package com.umeshgiri.otr.ticket.model;

import com.umeshgiri.otr.auth.model.User;
import com.umeshgiri.otr.commonmodel.AbstractEntity;
import com.umeshgiri.otr.ticket.payload.TicketAggregateInsightsPayload;
import com.umeshgiri.otr.ticket.payload.TicketDailyInsightsPayload;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NamedNativeQuery(
        name = "find_insights",
        query = "select count(*)                                        as totalBookedTickets," +
                "       count(if(isnull(p.id), NULL, 1))                as totalPaidTickets," +
                "       sum(if(isnull(p.id), 0, t.price))               as totalPaidAmount," +
                "       count(if(t.create_date >= CURDATE(), 1, NULL))  as totalBookedTicketsToday," +
                "       count(if(p.create_date >= CURDATE(), 1, NULL))  as totalPaidTicketsToday," +
                "       sum(if(p.create_date >= CURDATE(), t.price, 0)) as totalPaidAmountToday" +
                " from ticket t" +
                "         left join payment p on t.id = p.ticket_id",
        resultSetMapping = "find_insights_payload"
)
@SqlResultSetMapping(
        name = "find_insights_payload",
        classes = @ConstructorResult(
                targetClass = TicketAggregateInsightsPayload.class,
                columns = {
                        @ColumnResult(name = "totalBookedTickets", type = Long.class),
                        @ColumnResult(name = "totalPaidTickets", type = Long.class),
                        @ColumnResult(name = "totalPaidAmount", type = BigDecimal.class),
                        @ColumnResult(name = "totalBookedTicketsToday", type = Long.class),
                        @ColumnResult(name = "totalPaidTicketsToday", type = Long.class),
                        @ColumnResult(name = "totalPaidAmountToday", type = BigDecimal.class)
                }
        )
)
@NamedNativeQuery(
        name = "find_daily_insights",
        query = "select date(t.create_date)               as date," +
                "       count(*)                          as totalBookedTickets," +
                "       count(if(isnull(p.id), NULL, 1))  as totalPaidTickets," +
                "       count(if(isnull(p.id), 1, NULL))  as totalUnPaidTickets," +
                "       sum(if(isnull(p.id), 0, t.price)) as totalPaidAmount" +
                " from ticket t" +
                "         left join payment p on t.id = p.ticket_id" +
                " group by date(t.create_date)",
        resultSetMapping = "find_daily_insights_payload"
)
@SqlResultSetMapping(
        name = "find_daily_insights_payload",
        classes = @ConstructorResult(
                targetClass = TicketDailyInsightsPayload.class,
                columns = {
                        @ColumnResult(name = "date", type = String.class),
                        @ColumnResult(name = "totalBookedTickets", type = Long.class),
                        @ColumnResult(name = "totalPaidTickets", type = Long.class),
                        @ColumnResult(name = "totalUnPaidTickets", type = Long.class),
                        @ColumnResult(name = "totalPaidAmount", type = BigDecimal.class)
                }
        )
)
public class Ticket extends AbstractEntity {

    @OneToOne(mappedBy = "ticket")
    @Cascade(CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    private User user;

    //Assumed to be 100 for demo
    private BigDecimal price = new BigDecimal("100");

    public Boolean isPaymentDone() {
        return payment != null;
    }
}
