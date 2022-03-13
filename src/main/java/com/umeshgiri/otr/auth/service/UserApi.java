package com.umeshgiri.otr.auth.service;

import com.umeshgiri.otr.auth.model.User;
import com.umeshgiri.otr.auth.payload.UserDto;
import com.umeshgiri.otr.auth.repository.UserRepository;
import com.umeshgiri.otr.commonexception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApi {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter converter;

    public UserDto findDtoById(Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return converter.convert(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
}
