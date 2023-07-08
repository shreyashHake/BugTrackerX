package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    // default 'Admin' and 'User' of the application
    @PostConstruct
    public void initAdminUser() {
        userService.initRoleAndUser();;
    }

    @PostMapping({"/createNewUser"})
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }
    @PostMapping({"/createNewStaff"})
    public User createNewStaff(@RequestBody User user) {
        return userService.createNewStaff(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This is only for admin";
    }

    @GetMapping({"/forCustomer"})
    @PreAuthorize("hasRole('Customer')")
    public String forUser(){
        return "This is for all Customer";
    }

    @GetMapping({"/forStaff"})
    @PreAuthorize("hasRole('Staff')")
    public String forStaff(){
        return "This is for all Staff";
    }
}
