package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.User;

import java.util.List;


public interface IUserService {
    User createNewUser(User user);
    void initRoleAndUser();

    User createNewStaff(User staff);

    List<User> getAllUser();

//    List<User> getUserByRoleName(String roleName);
}
