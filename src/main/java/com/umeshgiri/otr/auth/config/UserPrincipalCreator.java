package com.umeshgiri.otr.auth.config;

import com.umeshgiri.otr.auth.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;


@Service
public class UserPrincipalCreator {

    public UserPrincipal create(User user) {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        user.getProfile().getRoleGroups().forEach(roleGroup -> roleGroup.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()))
        ));
        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
