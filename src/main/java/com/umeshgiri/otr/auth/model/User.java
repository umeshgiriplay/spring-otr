package com.umeshgiri.otr.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umeshgiri.otr.commonmodel.AbstractEntity;
import com.umeshgiri.otr.ticket.model.Ticket;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
public class User extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    @ManyToOne
    private Profile profile;

    @OneToMany(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Ticket> payment;
}
