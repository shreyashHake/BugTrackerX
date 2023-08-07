package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    // default 'Admin' and 'User' of the application
    @PostConstruct
    public void initAdminUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/createNewUser"})
    public User createNewUser(@RequestBody User user) {
        log.info("Inside:- /UserController/createNewUser");
        try {
            return userService.createNewUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new User();
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping({"/createNewStaff"})
    public User createNewStaff(@RequestBody User user) {
        log.info("Inside:- /UserController/createNewStaff");
        try {
            return userService.createNewStaff(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new User();
    }
}
