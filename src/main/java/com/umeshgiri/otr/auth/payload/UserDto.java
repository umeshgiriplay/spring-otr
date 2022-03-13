package com.umeshgiri.otr.auth.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UserDto {
    private String name;

    @Email
    private String email;
}
