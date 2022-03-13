package com.umeshgiri.otr.auth.service;

import com.umeshgiri.otr.auth.model.Profile;
import com.umeshgiri.otr.auth.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileApi {
    @Autowired
    private ProfileRepository profileRepository;

    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    public Profile getDefaultProfile() {
        return profileRepository.findById(1L).orElseGet(() ->
                new Profile("Default"));
    }
}
