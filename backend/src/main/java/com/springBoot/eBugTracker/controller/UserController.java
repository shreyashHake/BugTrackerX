package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

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
        return userService.createNewUser(user);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping({"/createNewStaff"})
    public User createNewStaff(@RequestBody User user) {
        return userService.createNewStaff(user);
    }
}
