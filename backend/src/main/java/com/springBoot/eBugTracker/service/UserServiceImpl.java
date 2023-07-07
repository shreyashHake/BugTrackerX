package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private com.springBoot.eBugTracker.repository.IUserRepository IUserRepository;

    @Autowired
    private com.springBoot.eBugTracker.repository.IRoleRepository IRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(User user) {
        Role role = IRoleRepository.findById("Customer").get();

        user.setUserRole(new HashSet<>(Collections.singletonList(role)));
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return IUserRepository.save(user);
    }

    public void initRoleAndUser() {
        //1. setting demo Admin :
        Role adminRole = new Role(
                "Admin",
                "Admin of the system"
        );
        IRoleRepository.save(adminRole);

        User admin = new User(
                "admin123",
                getEncodedPassword("Pass@123"),
                new HashSet<>(Collections.singletonList(adminRole))
        );
        IUserRepository.save(admin);

        //2. Setting demo Customer
        Role customerRole = new Role(
                "Customer",
                "Default role of newly registered user"
        );
        IRoleRepository.save(customerRole);

        User customer = new User(
                "customer123",
                getEncodedPassword("Pass@123"),
                new HashSet<>(Collections.singletonList(customerRole))

        );
        IUserRepository.save(customer);

        //3. Setting demo Staff
        Role staffRole = new Role(
                "Staff",
                "Those who will solve the bugs"
        );
        IRoleRepository.save(staffRole);

        User staff = new User(
                "staff123",
                getEncodedPassword("Pass@123"),
                new HashSet<>(Collections.singletonList(staffRole))

        );
        IUserRepository.save(staff);


    }

    // to get encrypted password
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
