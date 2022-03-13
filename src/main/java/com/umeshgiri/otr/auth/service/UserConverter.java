package com.umeshgiri.otr.auth.service;

import com.umeshgiri.otr.auth.model.User;
import com.umeshgiri.otr.auth.payload.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        return userDto;
    }
}
