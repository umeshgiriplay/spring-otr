package com.umeshgiri.otr.auth.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Profile extends AbstractEntity {
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<RoleGroup> roleGroups = new LinkedHashSet<>();


    public Profile(String name) {
        this.name = name;
    }
}
