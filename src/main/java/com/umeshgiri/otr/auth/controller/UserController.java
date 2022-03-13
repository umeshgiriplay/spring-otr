package com.umeshgiri.otr.auth.controller;

import com.umeshgiri.otr.auth.config.CurrentUser;
import com.umeshgiri.otr.auth.config.UserPrincipal;
import com.umeshgiri.otr.auth.payload.UserDto;
import com.umeshgiri.otr.auth.service.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserApi userApi;

    @GetMapping("/user/me")
    @PreAuthorize("hasAnyAuthority('read_self')")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userApi.findDtoById(userPrincipal.getId());
    }
}
