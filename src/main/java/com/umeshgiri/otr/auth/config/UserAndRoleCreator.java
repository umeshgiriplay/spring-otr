package com.umeshgiri.otr.auth.config;

import com.umeshgiri.otr.auth.model.Profile;
import com.umeshgiri.otr.auth.model.Role;
import com.umeshgiri.otr.auth.model.RoleGroup;
import com.umeshgiri.otr.auth.model.User;
import com.umeshgiri.otr.auth.repository.RoleGroupRepository;
import com.umeshgiri.otr.auth.repository.RoleRepository;
import com.umeshgiri.otr.auth.service.ProfileApi;
import com.umeshgiri.otr.auth.service.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UserAndRoleCreator implements ApplicationListener<ContextRefreshedEvent> {

    private static HashMap<String, Set<String>> groupsWithRoles = new HashMap<>();

    static {
        Set<String> roles;

        {
            roles = new HashSet<>();
            roles.add("read_self");
            groupsWithRoles.put("self_management", roles);
        }//self_management

        {
            roles = new HashSet<>();
            roles.add("read_self_ticket");
            roles.add("book_ticket");
            roles.add("make_payment");
            groupsWithRoles.put("ticket_booking", roles);
        }//book_ticket

        {
            roles = new HashSet<>();
            roles.add("ticket_insights");
            groupsWithRoles.put("ticket_insights", roles);
        }//ticket_insights
    }

    @Value("${app.defaults.user.name}")
    private String defaultUserName;

    @Value("${app.defaults.user.email}")
    private String defaultUserEmail;

    @Value("${app.defaults.user.password}")
    private String defaultUserPassword;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleGroupRepository roleGroupRepository;
    @Autowired
    private ProfileApi profileApi;
    @Autowired
    private UserApi userApi;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createRoleAndProfile() {
        Set<RoleGroup> roleGroups = new LinkedHashSet<>();
        groupsWithRoles.forEach(
                (groupName, roles) -> {
                    RoleGroup roleGroup =
                            roleGroupRepository.getRoleGroupByName(groupName)
                                    .orElseGet(() -> new RoleGroup(groupName));
                    Set<Role> roleList = new LinkedHashSet<>();
                    roles.forEach(roleName -> {
                        Role role =
                                roleRepository.getRoleByRoleName(roleName)
                                        .orElseGet(() -> roleRepository.save(new Role(roleName)));
                        roleList.add(role);
                    });
                    roleGroup.getRoles().addAll(roleList);
                    roleGroups.add(roleGroup);
                }
        );
        roleGroupRepository.saveAll(roleGroups);
        final Profile profile = profileApi.getDefaultProfile();
        profile.setRoleGroups(roleGroups);
        profileApi.save(profile);
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createRoleAndProfile();
        createUser();
    }

    private void createUser() {
        User user = userApi.findById(1L).orElseGet(User::new);
        user.setProfile(profileApi.getDefaultProfile());
        user.setName(defaultUserName);
        user.setEmail(defaultUserEmail);
        user.setPassword(passwordEncoder.encode(defaultUserPassword));
        userApi.save(user);
    }
}
