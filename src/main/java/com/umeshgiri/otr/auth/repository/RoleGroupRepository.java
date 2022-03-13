package com.umeshgiri.otr.auth.repository;

import com.umeshgiri.otr.auth.model.RoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {
    Optional<RoleGroup> getRoleGroupByName(String name);
}
