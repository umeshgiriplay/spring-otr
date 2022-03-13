package com.umeshgiri.otr.auth.repository;

import com.umeshgiri.otr.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByRoleName(String roleName);
}
