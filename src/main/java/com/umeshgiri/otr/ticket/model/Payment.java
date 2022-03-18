package com.umeshgiri.otr.ticket.model;

import com.umeshgiri.otr.commonmodel.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Payment extends AbstractEntity {

    @OneToOne
    private Ticket ticket;
}
