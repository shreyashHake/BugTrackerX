package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.User;


public interface UserService {
    User createNewUser(User user);
    void initRoleAndUser();

    User createNewStaff(User user);
}
