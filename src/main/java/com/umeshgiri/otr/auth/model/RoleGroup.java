package com.umeshgiri.otr.auth.model;

import com.umeshgiri.otr.commonmodel.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
@NoArgsConstructor
public class RoleGroup extends AbstractEntity {
    private String name;
    @OneToMany(
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private Set<Role> roles = new LinkedHashSet<>();

    public RoleGroup(String name) {
        this.name = name;
    }
}
