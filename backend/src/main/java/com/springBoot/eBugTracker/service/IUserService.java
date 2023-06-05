package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.User;


public interface IUserService {
    User createNewUser(User user);
    void initRoleAndUser();

    User createNewStaff(User staff);
}
