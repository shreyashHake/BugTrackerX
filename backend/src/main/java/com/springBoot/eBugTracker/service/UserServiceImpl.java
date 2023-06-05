package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.repository.IRoleRepository;
import com.springBoot.eBugTracker.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IRoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(User user) {
        Role role = roleRepo.findById("Customer").get();

        user.setUserRole(new HashSet<>(Collections.singletonList(role)));
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userRepo.save(user);
    }

    public void initRoleAndUser() {
        //1. setting demo Admin :
        Role adminRole = new Role(
                "Admin",
                "Admin of the system"
        );
        roleRepo.save(adminRole);

        User admin = new User(
                "admin123",
                "Admin",
                "Admin",
                getEncodedPassword("Pass@123"),
                new HashSet<>(Collections.singletonList(adminRole))
        );
        userRepo.save(admin);

        //2. Setting demo Customer
        Role customerRole = new Role(
                "Customer",
                "Default role of newly registered user"
        );
        roleRepo.save(customerRole);

        User customer = new User(
                "customer123",
                "Customer",
                "Customer",
                getEncodedPassword("Pass@123"),
                new HashSet<>(Collections.singletonList(customerRole))

        );
        userRepo.save(customer);

        //3. Setting demo Staff
        Role staffRole = new Role(
                "Staff",
                "Those who will solve the bugs"
        );
        roleRepo.save(staffRole);

        User staff = new User(
                "staff123",
                "Staff",
                "Staff",
                getEncodedPassword("Pass@123"),
                new HashSet<>(Collections.singletonList(staffRole))

        );
        userRepo.save(staff);


    }

    // to get encrypted password
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
