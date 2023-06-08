package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private IUserService userService;

    // default 'Admin' and 'User' of the application
    @PostConstruct
    public void initAdminUser() {
        userService.initRoleAndUser();;
    }

    @PostMapping({"/createNewUser"})
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @GetMapping({"/getAll"})
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAll() {
        return  userService.getAllUser();
    }

    // getting user by username
    @GetMapping("/getUserByUserName/{userName}")
    @PreAuthorize("hasRole('Admin')")
    public User getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

    // Updating user
    @PatchMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
