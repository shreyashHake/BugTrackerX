package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.User;

import java.util.List;


public interface IUserService {
    User createNewUser(User user);
    void initRoleAndUser();

    User createNewStaff(User staff);

    List<User> getAllUser();

    User deleteStaffById(String userName);

    User getUserByUserName(String userName);

    User updateUser(User user);
}
