package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {
    @Autowired
    private IUserService userService;

    @PostMapping({"/createNewStaff"})
    @PreAuthorize("hasRole('Admin')")
    public User createNewStaff(@RequestBody User staff) {
        return userService.createNewStaff(staff);
    }
}
