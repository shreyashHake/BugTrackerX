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
import java.util.List;

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

    @Override
    public User createNewStaff(User staff) {
        Role role = roleRepo.findById("Staff").get();

        staff.setUserRole(new HashSet<>(Collections.singletonList(role)));
        staff.setUserPassword(getEncodedPassword(staff.getUserPassword()));
        return userRepo.save(staff);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User deleteStaffById(String userName) {
        User user = userRepo.findUserByUserName(userName);
        if (user != null) {
            Role staffRole = roleRepo.findById("Staff").orElse(null);

            user.removeRole(staffRole);
            userRepo.save(user);

            userRepo.deleteById(userName);
        }
        return user;
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
