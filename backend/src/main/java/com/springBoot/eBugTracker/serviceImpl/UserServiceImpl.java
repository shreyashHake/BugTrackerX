package com.springBoot.eBugTracker.serviceImpl;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.service.UserService;
import com.springBoot.eBugTracker.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private com.springBoot.eBugTracker.repository.IUserRepository IUserRepository;

    @Autowired
    private com.springBoot.eBugTracker.repository.IRoleRepository IRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(User user) {
        log.info("Inside:- /UserServiceImpl/createNewUser");
        try {
            Optional<Role> optionalRole = IRoleRepository.findById("Customer");
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                user.setUserRole(new HashSet<>(Collections.singletonList(role)));
                user.setUserPassword(getEncodedPassword(user.getUserPassword()));
                return IUserRepository.save(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new User();
    }

    public void initRoleAndUser() {
        log.info("Inside:- /UserServiceImpl/initRoleAndUser");
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

    @Override
    public User createNewStaff(User user) {
        log.info("Inside:- /UserServiceImpl/createNewStaff");
        try {
            Optional<Role> optionalRole = IRoleRepository.findById("Staff");
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                user.setUserRole(new HashSet<>(Collections.singletonList(role)));
                user.setUserPassword(getEncodedPassword(user.getUserPassword()));
                return IUserRepository.save(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new User();
    }

    // to get encrypted password
    public String getEncodedPassword(String password) {
        log.info("Inside:- /UserServiceImpl/getEncodedPassword");
        try {
            return passwordEncoder.encode(password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.generalResponse();
    }

}
